package com.brocast.demo.integracion;

import com.brocast.demo.controller.ClienteController;
import com.brocast.demo.dto.ClienteDTO;
import com.brocast.demo.orm.ClienteORM;
import com.brocast.demo.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class TestClienteController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        clienteDTO = new ClienteDTO(2L,"Juan Perez", 123456789L, 5555555L, "password123");
    }

    @Test
    void testGuardarCliente() throws Exception {
        doNothing().when(clienteService).guardarCliente(anyString(), anyLong(), anyLong(), anyString());

        mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Juan Perez\",\"cedula\":123456789,\"telefono\":5555555,\"clave\":\"password123\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Cliente guardado"));
    }

    @Test
    void testMostrarCliente() throws Exception {
        ClienteORM clienteORM = new ClienteORM(2L,"Juan Perez", 123456789L, 5555555L, "password123");
        when(clienteService.consultarCliente(123456789L)).thenReturn(clienteORM);

        mockMvc.perform(get("/clientes")
                        .param("cedula", "123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cedula").value(123456789L))
                .andExpect(jsonPath("$.nombre").value("Juan Perez"));
    }
    @Test
    void testObtenerClientePorCedulaClienteEncontrado() throws Exception {
        ClienteORM clienteORM = new ClienteORM(2L,"Juan Perez", 123456789L, 5555555L, "password123");
        when(clienteService.consultarCliente(123456789L)).thenReturn(clienteORM);

        mockMvc.perform(get("/client")
                        .param("cedula", "123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cedula").value(123456789L))
                .andExpect(jsonPath("$.nombre").value("Juan Perez"));
    }

    @Test
    void testObtenerClientePorCedulaClienteNoEncontrado() throws Exception {
        when(clienteService.consultarCliente(123456789L)).thenReturn(null);

        mockMvc.perform(get("/client")
                        .param("cedula", "123456789"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGuardarClienteConExcepcion() throws Exception {
        doThrow(new RuntimeException("Error al guardar")).when(clienteService).guardarCliente(anyString(), anyLong(), anyLong(), anyString());

        mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Juan Perez\",\"cedula\":123456789,\"telefono\":5555555,\"clave\":\"password123\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error al guardar cliente: Error al guardar"));
    }

    @Test
    void testLoginExitoso() throws Exception {
        when(clienteService.validarCredenciales("Juan Perez", "password123")).thenReturn(true);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Juan Perez\",\"clave\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Login exitoso"));
    }

    @Test
    void testLoginFallido() throws Exception {
        when(clienteService.validarCredenciales("Juan Perez", "wrongpassword")).thenReturn(false);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Juan Perez\",\"clave\":\"wrongpassword\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Usuario o contraseña incorrectos"));
    }
}