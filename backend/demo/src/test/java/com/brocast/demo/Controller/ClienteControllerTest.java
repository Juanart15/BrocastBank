package com.brocast.demo.Controller;

import com.brocast.demo.DTO.ClienteDTO;
import com.brocast.demo.ORM.ClienteORM;
import com.brocast.demo.Services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarCliente() {
        // Datos de prueba
        ClienteDTO clienteDTO = new ClienteDTO(444444l, " Juan Camilo ", 1021393868l,313979880l, "clave123");
        doNothing().when(clienteService).guardarCliente(anyString(), anyLong(), anyLong(), anyString());
        ResponseEntity<String> response = clienteController.guardarCliente(clienteDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Cliente guardado", response.getBody());
        verify(clienteService, times(1)).guardarCliente(clienteDTO.nombre(), clienteDTO.cedula(), clienteDTO.telefono(), clienteDTO.clave());
    }

    @Test
    void mostrarCliente() {

        Long cedula = 12345678L;
        ClienteORM clienteORM = new ClienteORM();
        clienteORM.setCedula(cedula);
        when(clienteService.consultarCliente(cedula)).thenReturn(clienteORM);
        ClienteORM result = clienteController.mostrarCliente(cedula);
        assertEquals(cedula, result.getCedula());
        verify(clienteService, times(1)).consultarCliente(cedula);
    }

    @Test
    void obtenerClientePorCedula() {
        // Datos de prueba
        Long cedula = 12345678L;
        ClienteORM clienteORM = new ClienteORM();
        clienteORM.setCedula(cedula);
        when(clienteService.consultarCliente(cedula)).thenReturn(clienteORM);
        ResponseEntity<ClienteORM> response = clienteController.obtenerClientePorCedula(cedula);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteORM, response.getBody());
        verify(clienteService, times(1)).consultarCliente(cedula);
    }

    @Test
    void login() {

        ClienteDTO clienteDTO = new ClienteDTO(55555l, "juan camilo", null, 3138797780l, "clave123");

        when(clienteService.validarCredenciales(clienteDTO.nombre(), clienteDTO.clave())).thenReturn(true);
        ResponseEntity<String> response = clienteController.login(clienteDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login exitoso", response.getBody());
        verify(clienteService, times(1)).validarCredenciales(clienteDTO.nombre(), clienteDTO.clave());
        when(clienteService.validarCredenciales(clienteDTO.nombre(), clienteDTO.clave())).thenReturn(false);
        response = clienteController.login(clienteDTO);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Usuario o contraseña incorrectos", response.getBody());
        verify(clienteService, times(2)).validarCredenciales(clienteDTO.nombre(), clienteDTO.clave());
    }
}
