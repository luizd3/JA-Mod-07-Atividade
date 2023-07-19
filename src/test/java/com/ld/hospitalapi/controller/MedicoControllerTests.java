package com.ld.hospitalapi.controller;

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
}
