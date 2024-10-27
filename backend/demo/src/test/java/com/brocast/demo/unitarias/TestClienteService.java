package com.brocast.demo.unitarias;


import com.brocast.demo.jpa.ClienteJPA;
import com.brocast.demo.orm.ClienteORM;
import com.brocast.demo.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestClienteService {
    @Mock
    private ClienteJPA clienteJPA;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarCliente() {
        ClienteORM cliente = new ClienteORM(null, "Juan", 12345678L, 98765432L, "clave123");
        clienteService.guardarCliente("Juan", 12345678L, 98765432L, "clave123");
        verify(clienteJPA, times(1)).save(cliente);
    }

    @Test
    void testConsultarCliente() {
        Long cedula = 12345678L;
        ClienteORM cliente = new ClienteORM(1L, "Juan", cedula, 98765432L, "clave123");
        when(clienteJPA.findByCedula(cedula)).thenReturn(cliente);
        ClienteORM result = clienteService.consultarCliente(cedula);
        assertNotNull(result);
        assertEquals(cedula, result.getCedula());
        assertEquals("Juan", result.getNombre());
    }

    @Test
    void testValidarCredenciales() {
        String nombre = "Juan";
        String clave = "clave123";
        ClienteORM cliente = new ClienteORM(1L, nombre, 12345678L, 98765432L, clave);
        when(clienteJPA.findByNombre(nombre)).thenReturn(cliente);
        boolean resultado = clienteService.validarCredenciales(nombre, clave);
        assertTrue(resultado);  // Verifica que las credenciales sean correctas
    }

    @Test
    void testValidarCredencialesIncorrectas() {
        String nombre = "Juan";
        String claveIncorrecta = "claveIncorrecta";
        ClienteORM cliente = new ClienteORM(1L, nombre, 12345678L, 98765432L, "clave123");
        when(clienteJPA.findByNombre(nombre)).thenReturn(cliente);
        boolean resultado = clienteService.validarCredenciales(nombre, claveIncorrecta);
        assertFalse(resultado);  // Verifica que las credenciales incorrectas devuelvan false
    }

    @Test
    void testValidarCredencialesClienteNoEncontrado() {
        String nombre = "Juan";
        when(clienteJPA.findByNombre(nombre)).thenReturn(null);  // Simula que no se encuentra el cliente
        boolean resultado = clienteService.validarCredenciales(nombre, "clave123");
        assertFalse(resultado);  // Verifica que si no encuentra el cliente, las credenciales no son válidas
    }
}
