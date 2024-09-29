package com.brocast.demo.Controller;

import com.brocast.demo.Services.DepositoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = DepositoController.class)
class DepositoControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private DepositoService depositoService;

    @InjectMocks
    private DepositoController depositoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(depositoController).build();
    }

    @Test
    void guardarDeposito_Exitoso() throws Exception {
        // Simulando comportamiento exitoso del servicio sin errores
        mockMvc.perform(post("/deposito")
                        .param("numeroCuentaDeposito", "123456789")
                        .param("saldoDeposito", "500.0")
                        .param("claveCuentaDeposito", "clave123"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Deposito Registrado"))
                .andDo(print());

        verify(depositoService).guardarDepositos(anyLong(), anyDouble(), anyString());
    }

    @Test
    void guardarDeposito_ErrorValidacion() throws Exception {
        // Simulación: Parámetros faltantes o inválidos
        mockMvc.perform(post("/deposito")
                        .param("numeroCuentaDeposito", "")
                        .param("saldoDeposito", "500.0")
                        .param("claveCuentaDeposito", ""))
                .andExpect(status().isBadRequest())  // Esperamos un error 400 por falta de parámetros
                .andExpect(content().string("Error al guardar el deposito"))
                .andDo(print());
    }

    @Test
    void guardarDeposito_Unauthorized() throws Exception {
        // Simular un error 401 debido a credenciales incorrectas
        doThrow(new RuntimeException("Credenciales inválidas")).when(depositoService).guardarDepositos(anyLong(), anyDouble(), anyString());

        mockMvc.perform(post("/deposito")
                        .param("numeroCuentaDeposito", "123456789")
                        .param("saldoDeposito", "500.0")
                        .param("claveCuentaDeposito", "claveIncorrecta"))
                .andExpect(status().isUnauthorized())  // Esperamos un error 401 por credenciales incorrectas
                .andExpect(content().string("Error al guardar el deposito: Credenciales inválidas"))
                .andDo(print());

        verify(depositoService).guardarDepositos(anyLong(), anyDouble(), anyString());
    }

    @Test
    void guardarDeposito_InternalServerError() throws Exception {
        doThrow(new RuntimeException("Error en el servidor")).when(depositoService).guardarDepositos(anyLong(), anyDouble(), anyString());

        mockMvc.perform(post("/deposito")
                        .param("numeroCuentaDeposito", "123456789")
                        .param("saldoDeposito", "500.0")
                        .param("claveCuentaDeposito", "clave123"))
                .andExpect(status().isInternalServerError())  // Esperamos un error 500 por fallo del servidor
                .andExpect(content().string("Error al guardar el deposito: Error en el servidor"))
                .andDo(print());

        verify(depositoService).guardarDepositos(anyLong(), anyDouble(), anyString());
    }
}
