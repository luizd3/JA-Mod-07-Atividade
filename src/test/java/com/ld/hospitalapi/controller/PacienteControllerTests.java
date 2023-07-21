package com.ld.hospitalapi.controller;

import com.ld.hospitalapi.entities.PacienteEntity;
import com.ld.hospitalapi.repositories.PacienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureMockMvc
public class PacienteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    public void shouldListAllPacients() throws Exception {
        String requestBody = """
                {
                   "nome": "João Silva",
                   "telefone": "11999999999",
                   "dataNascimento": "2015-01-01"
                }""";

        String expectedResponseBody = """
                [{"id":1,"nome":"João Silva","telefone":"11999999999","dataNascimento":"2015-01-01"}]""";

        // Requisição para criar um paciente na base de dados
        mockMvc.perform(MockMvcRequestBuilders
                .post("/hospital/pacientes")
                .content(requestBody)
                .contentType("application/json"));

        // Requisição para testar se o status HTTP é 200 (OK)
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/hospital/pacientes"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        // Testa se o corpo da resposta é igual ao esperado
        assertEquals(expectedResponseBody, responseBody);

    }

    @Test
    public void shouldReturnAPacientGivenItsId() throws Exception {
        PacienteEntity paciente1 = getPacienteDefault();
        PacienteEntity paciente2 = getPacienteDefault();
        paciente2.setId(2L);
        paciente2.setNome("Hugo Oliveira");
        pacienteRepository.save(paciente1);
        pacienteRepository.save(paciente2);

        String expectedResponseBody = """
                {"id":2,"nome":"Hugo Oliveira","telefone":"11999999999","dataNascimento":"2015-01-01"}""";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/pacientes/2"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldCreateANewPacient() throws Exception {
        String requestBody = """
                {
                   "nome": "João Silva",
                   "telefone": "11999999999",
                   "dataNascimento": "2015-01-01"
                }""";

        // Testa apenas se o status HTTP é 201 (CREATED)
        mockMvc.perform(MockMvcRequestBuilders
                .post("/hospital/pacientes")
                .content(requestBody)
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateAPacient() throws Exception {
        String newPacientRequestBody = """
                {
                   "nome": "João Silva",
                   "telefone": "11999999999",
                   "dataNascimento": "2015-01-01"
                }""";

        String updatePacientRequestBody = """
                {
                   "id": 1,
                   "nome": "José Augusto",
                   "telefone": "11888888888",
                   "dataNascimento": "2015-01-01"
                }""";

        String expectedResponseBody = """
                [{"id":1,"nome":"José Augusto","telefone":"11888888888","dataNascimento":"2015-01-01"}]""";

        // Cadastrar um paciente na base de dados
        mockMvc.perform(MockMvcRequestBuilders
                .post("/hospital/pacientes")
                .content(newPacientRequestBody)
                .contentType("application/json"));

        // Requisição para atualizar o paciente criado anteriormente
        mockMvc.perform(MockMvcRequestBuilders
                .put("/hospital/pacientes")
                .content(updatePacientRequestBody)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("Paciente atualizado"));

        // Obter o paciente atualizado
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/pacientes"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldDeleteAPacient() throws Exception {
        String requestBody = """
                {
                   "nome": "João Silva",
                   "telefone": "11999999999",
                   "dataNascimento": "2015-01-01"
                }""";

        String expectedResponseBody = """
                []""";

        // Requisição POST para criar um paciente na base de dados
        mockMvc.perform(MockMvcRequestBuilders
                .post("/hospital/pacientes")
                .contentType("application/json")
                .content(requestBody));

        // Requisição DELETE para testar se o status HTTP é 204 (No Content)
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/hospital/pacientes/1"))
                .andExpect(status().isNoContent())
                .andReturn();

        // Requisição GET para obter a lista vazia após a exclusão do registro
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/pacientes"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        // Testa se o corpo da resposta é igual ao esperado (lista vazia)
        assertEquals(expectedResponseBody, responseBody);
    }

    private PacienteEntity getPacienteDefault() {
        PacienteEntity paciente = new PacienteEntity();
        paciente.setId(1L);
        paciente.setNome("João Silva");
        paciente.setTelefone("11999999999");
        paciente.setDataNascimento(LocalDate.parse("2015-01-01"));
        return paciente;
    }
}
