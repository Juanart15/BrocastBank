package com.brocast.demo.integracion;

import com.brocast.demo.controller.DepositoController;
import com.brocast.demo.dto.DepositoDTO;
import com.brocast.demo.services.DepositoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(DepositoController.class)
class TestDepositoController {

    private MockMvc mockMvc;

    @MockBean
    private DepositoService depositoService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new DepositoController(depositoService)).build();
    }

    @Test
    void testGuardarDeposito_Success() throws Exception {
        DepositoDTO depositoDTO = new DepositoDTO(123456L, 100.0, "claveCorrecta");

        when(depositoService.guardarDepositos(123456L, 100.0, "claveCorrecta")).thenReturn(true);

        mockMvc.perform(post("/deposito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depositoDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Deposito Registrado"));

        verify(depositoService, times(1)).guardarDepositos(123456L, 100.0, "claveCorrecta");
    }

    @Test
    void testGuardarDeposito_Error() throws Exception {
        DepositoDTO depositoDTO = new DepositoDTO(123456L, 100.0, "claveIncorrecta");
        doThrow(new RuntimeException("Clave incorrecta para la cuenta")).when(depositoService)
                .guardarDepositos(any(Long.class), any(Double.class), any(String.class));

        mockMvc.perform(post("/deposito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depositoDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error al guardar el deposito: Clave incorrecta para la cuenta"));

        verify(depositoService, times(1)).guardarDepositos(123456L, 100.0, "claveIncorrecta");
    }
}
