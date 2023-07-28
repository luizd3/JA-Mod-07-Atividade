package com.ld.hospitalapi.controller;

import com.ld.hospitalapi.entities.InternacaoEntity;
import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.entities.PacienteEntity;
import com.ld.hospitalapi.repositories.InternacaoRepository;
import com.ld.hospitalapi.repositories.MedicoRepository;
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
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureMockMvc
public class InternacaoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    InternacaoRepository internacaoRepository;

    @Test
    public void shouldListAllHospitalizations() throws Exception {
        medicoRepository.save(getMedicoDefault());
        pacienteRepository.save(getPacienteDefault());
        internacaoRepository.save(getInternacaoDefault());

        String expectedResponseBody = """
                [{"id":1,"paciente":{"id":1,"nome":"João Silva","telefone":"11999999999","dataNascimento":"2015-01-01"},"dataEntradaPaciente":"2023-04-01T10:30:15","dataSaidaPaciente":"2023-04-03T07:11:56","diagnostico":"diagnóstico teste","medico":{"matricula":1,"nome":"Marcelo Gomes","cargo":"Ortopedista","departamento":"PS","telefone":"11888888888"}}]""";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/internacoes"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldReturnAHospitalizationGivenItsId() throws Exception {
        medicoRepository.save(getMedicoDefault());
        pacienteRepository.save(getPacienteDefault());
        internacaoRepository.save(getInternacaoDefault());
        InternacaoEntity internacao2 = getInternacaoDefault();
        internacao2.setDiagnostico("diagnóstico 2");
        internacaoRepository.save(internacao2);

        String expectedResponseBody = """
                {"id":2,"paciente":{"id":1,"nome":"João Silva","telefone":"11999999999","dataNascimento":"2015-01-01"},"dataEntradaPaciente":"2023-04-01T10:30:15","dataSaidaPaciente":"2023-04-03T07:11:56","diagnostico":"diagnóstico 2","medico":{"matricula":1,"nome":"Marcelo Gomes","cargo":"Ortopedista","departamento":"PS","telefone":"11888888888"}}""";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/internacoes/2"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldReturnTotalHospitalizationsByPacient() throws Exception {
        PacienteEntity paciente1 = new PacienteEntity(1L, "Paciente 1", "11999999999", LocalDate.parse("2015-01-01"));
        PacienteEntity paciente2 = new PacienteEntity(2L, "Paciente 2", "11999999999", LocalDate.parse("1987-01-01"));
        pacienteRepository.save(paciente1);
        pacienteRepository.save(paciente2);

        MedicoEntity medico = new MedicoEntity(1L, "Medico 1", "Cargo 1", "Departamento 1", "11999999999");
        medicoRepository.save(medico);

        InternacaoEntity internacao1 = new InternacaoEntity(1L, paciente1, LocalDateTime.parse("2023-04-01T10:30:15"), LocalDateTime.parse("2023-04-03T07:11:56"), "Diagnóstico teste", medico);
        InternacaoEntity internacao2 = new InternacaoEntity(2L, paciente2, LocalDateTime.parse("2023-04-01T10:30:15"), LocalDateTime.parse("2023-04-03T07:11:56"), "Diagnóstico teste", medico);
        InternacaoEntity internacao3 = new InternacaoEntity(3L, paciente2, LocalDateTime.parse("2023-04-01T10:30:15"), LocalDateTime.parse("2023-04-03T07:11:56"), "Diagnóstico teste", medico);
        internacaoRepository.save(internacao1);
        internacaoRepository.save(internacao2);
        internacaoRepository.save(internacao3);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/internacoes/total-por-paciente"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        String expectedResponseBody = """
                [{"nomePaciente":"Paciente 2","quantidadeDeInternacoes":2},{"nomePaciente":"Paciente 1","quantidadeDeInternacoes":1}]""";

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldCreateANewHospitalization() throws Exception {
        medicoRepository.save(getMedicoDefault());
        pacienteRepository.save(getPacienteDefault());

        String hospitalizationRequestBody = """
                {
                    "paciente": {
                        "id": 1
                    },
                    "dataEntradaPaciente": "2023-04-01T10:30:15",
                    "dataSaidaPaciente": "2023-04-03T07:11:56",
                    "diagnostico": "diagnóstico teste",
                    "medico": {
                        "matricula": 1
                    }
                }""";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/hospital/internacoes")
                .content(hospitalizationRequestBody)
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateAHospitalization() throws Exception {
        medicoRepository.save(getMedicoDefault());
        pacienteRepository.save(getPacienteDefault());
        internacaoRepository.save(getInternacaoDefault());

        String updatedHospitalizationRequestBody = """
                {
                    "id": 1,
                    "paciente": {
                        "id": 1
                    },
                    "dataEntradaPaciente": "2023-04-01T10:30:15",
                    "dataSaidaPaciente": "2023-04-03T07:11:56",
                    "diagnostico": "diagnóstico teste atualizado",
                    "medico": {
                        "matricula": 1
                    }
                }""";

        String expectedResponseBody = """
                [{"id":1,"paciente":{"id":1,"nome":"João Silva","telefone":"11999999999","dataNascimento":"2015-01-01"},"dataEntradaPaciente":"2023-04-01T10:30:15","dataSaidaPaciente":"2023-04-03T07:11:56","diagnostico":"diagnóstico teste atualizado","medico":{"matricula":1,"nome":"Marcelo Gomes","cargo":"Ortopedista","departamento":"PS","telefone":"11888888888"}}]""";

        mockMvc.perform(MockMvcRequestBuilders
                .put("/hospital/internacoes")
                .content(updatedHospitalizationRequestBody)
                .contentType("application/json"))
                .andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/internacoes"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void shouldDeleteAHospitalization() throws Exception {
        medicoRepository.save(getMedicoDefault());
        pacienteRepository.save(getPacienteDefault());
        internacaoRepository.save(getInternacaoDefault());

        String expectedResponseBody = """
                []""";

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/hospital/internacoes/1"))
                .andExpect(status().isNoContent())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/internacoes"))
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

    private PacienteEntity getPacienteDefault() {
        PacienteEntity paciente = new PacienteEntity();
        paciente.setId(1L);
        paciente.setNome("João Silva");
        paciente.setTelefone("11999999999");
        paciente.setDataNascimento(LocalDate.parse("2015-01-01"));
        return paciente;
    }

    private InternacaoEntity getInternacaoDefault() {
        InternacaoEntity internacao = new InternacaoEntity();
        internacao.setPaciente(getPacienteDefault());
        internacao.setDataEntradaPaciente(LocalDateTime.parse("2023-04-01T10:30:15"));
        internacao.setDataSaidaPaciente(LocalDateTime.parse("2023-04-03T07:11:56"));
        internacao.setDiagnostico("diagnóstico teste");
        internacao.setMedico(getMedicoDefault());
        return internacao;
    }
}
