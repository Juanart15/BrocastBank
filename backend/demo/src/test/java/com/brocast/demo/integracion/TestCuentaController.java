package com.brocast.demo.integracion;

import com.brocast.demo.dto.CuentaDTO;
import com.brocast.demo.orm.CuentaORM;
import com.brocast.demo.controller.CuentaController;
import com.brocast.demo.services.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuentaController.class)
class TestCuentaController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    private CuentaORM cuentaORM;

    @BeforeEach
    void setUp() {
        CuentaDTO cuentaDTO = new CuentaDTO(123456789L, 1500.00, "clave123");
        cuentaORM = new CuentaORM();
        cuentaORM.setClienteCedula(123456789L);
        cuentaORM.setCuentaSaldo(1500.00);
        cuentaORM.setCuentaClave("clave123");
    }
    @Test
    void testGuardarCuentaException() throws Exception {
        when(cuentaService.guardarCuenta(123456789L,1500.00,"clave123")).thenThrow(new RuntimeException("Error al guardar"));

        mockMvc.perform(post("/cuenta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"clienteCedula\": 123456789, \"cuentaSaldo\": 1500.00, \"cuentaClave\": \"clave123\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error al crear la cuenta: Error al guardar"));
    }

    @Test
    void testGuardarCuenta() throws Exception {
        when(cuentaService.guardarCuenta(123456789L,1500.00,"clave123")).thenReturn(true);

        mockMvc.perform(post("/cuenta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"clienteCedula\": 123456789, \"cuentaSaldo\": 1500.00, \"cuentaClave\": \"clave123\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testMostrarCuenta() throws Exception {
        when(cuentaService.consultarCuenta(123456789L)).thenReturn(cuentaORM);

        mockMvc.perform(get("/cuentas")
                        .param("clienteCedula", "123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteCedula").value(123456789L))
                .andExpect(jsonPath("$.cuentaSaldo").value(1500.00));
    }

    @Test
    void testConsultarCuenta() throws Exception {
        when(cuentaService.consultarCuenta(123456789L, "clave123")).thenReturn(cuentaORM);

        mockMvc.perform(get("/cuent")
                        .param("clienteCedula", "123456789")
                        .param("cuentaClave", "clave123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteCedula").value(123456789L))
                .andExpect(jsonPath("$.cuentaSaldo").value(1500.00));
    }

    @Test
    void testConsultarCuentaInvalida() throws Exception {
        when(cuentaService.consultarCuenta(123456789L, "claveIncorrecta")).thenThrow(new RuntimeException("Cuenta no encontrada"));

        mockMvc.perform(get("/cuent")
                        .param("clienteCedula", "123456789")
                        .param("cuentaClave", "claveIncorrecta"))
                .andExpect(status().isBadRequest());
    }
}
