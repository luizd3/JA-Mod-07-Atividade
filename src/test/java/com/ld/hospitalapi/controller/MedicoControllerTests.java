package com.ld.hospitalapi.controller;

import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.repositories.MedicoRepository;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureMockMvc
public class MedicoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MedicoRepository medicoRepository;

    @Test
    public void shouldListAllDoctors() throws Exception {
        String requestBody = """
                {
                    "nome": "Marcelo Goes",
                    "cargo": "Otorrinolaringologista",
                    "departamento": "PS",
                    "telefone": "11888888888"
                }""";

        String expectedResponseBody = """
                [{"matricula":1,"nome":"Marcelo Goes","cargo":"Otorrinolaringologista","departamento":"PS","telefone":"11888888888"}]""";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/hospital/medicos")
                        .content(requestBody)
                        .contentType("application/json"))
                .andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/hospital/medicos"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldReturnADoctorGivenItsId() throws Exception {
        MedicoEntity medico1 = getMedicoDefault();
        MedicoEntity medico2 = getMedicoDefault();
        medico2.setMatricula(2L);
        medico2.setNome("Marcos Antônio");

        medicoRepository.save(medico1);
        medicoRepository.save(medico2);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/hospital/medicos/2"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        String expectedResponseBody = """
                {"matricula":2,"nome":"Marcos Antônio","cargo":"Ortopedista","departamento":"PS","telefone":"11888888888"}""";

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldReturnTotalDoctorsByDepartment() throws Exception {
        MedicoEntity medico1 = new MedicoEntity(1L, "Médico 1", "Cargo 1", "Departamento 1", "11888888888");
        MedicoEntity medico2 = new MedicoEntity(2L, "Médico 2", "Cargo 2", "Departamento 1", "11888888888");
        MedicoEntity medico3 = new MedicoEntity(3L, "Médico 3", "Cargo 3", "Departamento 1", "11888888888");
        MedicoEntity medico4 = new MedicoEntity(4L, "Médico 4", "Cargo 4", "Departamento 2", "11888888888");
        MedicoEntity medico5 = new MedicoEntity(5L, "Médico 5", "Cargo 5", "Departamento 2", "11888888888");

        medicoRepository.save(medico1);
        medicoRepository.save(medico2);
        medicoRepository.save(medico3);
        medicoRepository.save(medico4);
        medicoRepository.save(medico5);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/medicos/total-por-departamento"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        String expectedResponseBody = """
                [{"departamento":"Departamento 2","quantidadeDeMedicos":2},{"departamento":"Departamento 1","quantidadeDeMedicos":3}]""";

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldReturnDoctorsByDepartment() throws Exception {
        MedicoEntity medico1 = new MedicoEntity(1L, "Médico 1", "Cargo 1", "Departamento 1", "11888888888");
        MedicoEntity medico2 = new MedicoEntity(2L, "Médico 2", "Cargo 2", "Departamento 1", "11888888888");
        MedicoEntity medico3 = new MedicoEntity(3L, "Médico 3", "Cargo 3", "Departamento 1", "11888888888");
        MedicoEntity medico4 = new MedicoEntity(4L, "Médico 4", "Cargo 4", "Departamento 2", "11888888888");
        MedicoEntity medico5 = new MedicoEntity(5L, "Médico 5", "Cargo 5", "Departamento 2", "11888888888");

        medicoRepository.save(medico1);
        medicoRepository.save(medico2);
        medicoRepository.save(medico3);
        medicoRepository.save(medico4);
        medicoRepository.save(medico5);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/medicos/por-departamento"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        String expectedResponseBody = """
                [{"departamento":"Departamento 1","medicos":["Médico 1","Médico 2","Médico 3"]},{"departamento":"Departamento 2","medicos":["Médico 4","Médico 5"]}]""";

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldCreateANewDoctor() throws Exception {
        String requestBody = """
                {
                    "nome": "Marcelo Goes",
                    "cargo": "Otorrinolaringologista",
                    "departamento": "PS",
                    "telefone": "11888888888"
                }""";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/hospital/medicos")
                        .content(requestBody)
                        .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateADoctor() throws Exception {
        String requestBody = """
                {
                    "nome": "Marcelo Goes",
                    "cargo": "Otorrinolaringologista",
                    "departamento": "PS",
                    "telefone": "11888888888"
                }""";

        String updatedRequestBody = """
                {
                    "matricula": 1,
                    "nome": "Marcelo Gomes",
                    "cargo": "Ortopedista",
                    "departamento": "PS",
                    "telefone": "11888888888"
                }""";

        String expectedResponseBody = """
                [{"matricula":1,"nome":"Marcelo Gomes","cargo":"Ortopedista","departamento":"PS","telefone":"11888888888"}]""";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/hospital/medicos")
                .content(requestBody)
                .contentType("application/json"));

        mockMvc.perform(MockMvcRequestBuilders
                .put("/hospital/medicos")
                .content(updatedRequestBody)
                .contentType("application/json"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/medicos"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldDeleteADoctor() throws Exception {
        String requestBody = """
                {
                    "nome": "Marcelo Goes",
                    "cargo": "Otorrinolaringologista",
                    "departamento": "PS",
                    "telefone": "11888888888"
                }""";

        String expectedResponseBody = """
                []""";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/hospital/medicos")
                .content(requestBody)
                .contentType("application/json"))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/hospital/medicos/1"))
                .andExpect(status().isNoContent())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/medicos"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    private MedicoEntity getMedicoDefault() {
        MedicoEntity medico = new MedicoEntity();
        medico.setMatricula(1L);
        medico.setNome("Marcelo Gomes");
        medico.setCargo("Ortopedista");
        medico.setDepartamento("PS");
        medico.setTelefone("11888888888");
        return medico;
    }
}
