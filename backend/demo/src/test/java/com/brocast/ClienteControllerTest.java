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
import static org.junit.jupiter.api.Assertions.*;
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
	void testGuardarCliente() {
		ClienteDTO clienteDTO = new ClienteDTO("juan", 123456789L, "5551234556", "password");
		doNothing().when(clienteService).guardarCliente(anyString(), anyLong(), anyString(), anyString());

		ResponseEntity<String> response = clienteController.guardarCliente(clienteDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Cliente guardado", response.getBody());
	}

	@Test
	void testGuardarClienteException() {
		ClienteDTO clienteDTO = new ClienteDTO("juan", 123456789L, "5551234556", "password");
		doThrow(new RuntimeException("Error")).when(clienteService).guardarCliente(anyString(), anyLong(), anyString(), anyString());

		ResponseEntity<String> response = clienteController.guardarCliente(clienteDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getBody().contains("Error al guardar cliente"));
	}

	@Test
	void testMostrarCliente() {
		ClienteORM clienteORM = new ClienteORM(123456789L, "juan Doe", "5551234556", "password");
		when(clienteService.consultarCliente(anyLong())).thenReturn(clienteORM);

		ClienteORM response = clienteController.mostrarCliente(123456789L);

		assertNotNull(response);
		assertEquals("juan Doe", response.getNombre());
	}

	@Test
	void testLoginExitoso() {
		ClienteDTO clienteDTO = new ClienteDTO("juan Doe", 123456789L,"5551234556" , "password");
		when(clienteService.validarCredenciales(anyString(), anyString())).thenReturn(true);

		ResponseEntity<String> response = clienteController.login(clienteDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Login exitoso", response.getBody());
	}

	@Test
	void testLoginFallido() {
		ClienteDTO clienteDTO = new ClienteDTO("juan Doe", 123456789L, "5551234566", "password");
		when(clienteService.validarCredenciales(anyString(), anyString())).thenReturn(false);

		ResponseEntity<String> response = clienteController.login(clienteDTO);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertEquals("Usuario o contraseña incorrectos", response.getBody());
	}
}
