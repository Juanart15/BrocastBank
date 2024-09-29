package com.brocast.demo.Controller;

import com.brocast.demo.ORM.CuentaORM;
import com.brocast.demo.Services.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = CuentaController.class)
class CuentaControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    @InjectMocks
    private CuentaController cuentaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(cuentaController).build();
    }

    @Test
    void guardarCuenta() throws Exception {
        doNothing().when(cuentaService).guardarCuenta(anyLong(), anyDouble(), anyString());
        mockMvc.perform(post("/cuenta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"clienteCedula\": 123456789, \"cuentaSaldo\": 1000.0, \"cuentaClave\": \"clave123\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Cuenta guardada"))
                .andDo(print());
        verify(cuentaService, times(1)).guardarCuenta(anyLong(), anyDouble(), anyString());
    }

    @Test
    void mostrarCuenta() throws Exception {
        CuentaORM cuentaMock = new CuentaORM(123456789L, 1000.0, "clave123");
        when(cuentaService.consultarCuenta(anyLong())).thenReturn(cuentaMock);
        mockMvc.perform(get("/cuentas")
                        .param("clienteCedula", "123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteCedula").value(123456789L))
                .andExpect(jsonPath("$.cuentaSaldo").value(1000.0))
                .andDo(print());

        // Verificar que el método del servicio se haya llamado
        verify(cuentaService, times(1)).consultarCuenta(anyLong());
    }

    @Test
    void consultarCuenta() throws Exception {

        CuentaORM cuentaMock = new CuentaORM(123456789L, 1000.0, "clave123");
        when(cuentaService.consultarCuenta(anyLong(), anyString())).thenReturn(cuentaMock);
        mockMvc.perform(get("/cuent")
                        .param("clienteCedula", "123456789")
                        .param("cuentaClave", "clave123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteCedula").value(123456789L))
                .andExpect(jsonPath("$.cuentaSaldo").value(1000.0))
                .andDo(print());
        verify(cuentaService, times(1)).consultarCuenta(anyLong(), anyString());
    }
}
