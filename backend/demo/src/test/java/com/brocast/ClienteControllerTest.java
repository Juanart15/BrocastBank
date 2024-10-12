/*package com.brocast;

import com.brocast.demo.Controller.ClienteController;
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
		ClienteDTO clienteDTO = new ClienteDTO(14522222L, "Juan", 512223123L, 312223123L,"password");
		doNothing().when(clienteService).guardarCliente(anyString(), anyLong(), Long.valueOf(anyString()), anyString());
		ResponseEntity<String> response = clienteController.guardarCliente(clienteDTO);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Cliente guardado", response.getBody());
	}

	@Test
	void testGuardarClienteException() {
		ClienteDTO clienteDTO = new ClienteDTO(14522222L, "Juan", 512223123L, 312223123L,"password");
		doThrow(new RuntimeException("Error")).when(clienteService).guardarCliente(anyString(), anyLong(), Long.valueOf(anyString()), anyString());

		ResponseEntity<String> response = clienteController.guardarCliente(clienteDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getBody().contains("Error al guardar cliente"));
	}

	@Test
	void testMostrarCliente() {
		ClienteORM clienteORM = new ClienteORM();
		when(clienteService.consultarCliente(anyLong())).thenReturn(clienteORM);

		ClienteORM response = clienteController.mostrarCliente(123456789L);

		assertNotNull(response);
		assertEquals("juan Doe", response.getNombre());
	}

	@Test
	void testLoginExitoso() {
		ClienteDTO clienteDTO = new ClienteDTO(14522222L, "Juan", 512223123L, 312223123L,"password");
		when(clienteService.validarCredenciales(anyString(), anyString())).thenReturn(true);

		ResponseEntity<String> response = clienteController.login(clienteDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Login exitoso", response.getBody());
	}

	@Test
	void testLoginFallido() {
		ClienteDTO clienteDTO = new ClienteDTO(14522222L, "Juan", 512223123L, 312223123L,"password");
		when(clienteService.validarCredenciales(anyString(), anyString())).thenReturn(false);

		ResponseEntity<String> response = clienteController.login(clienteDTO);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertEquals("Usuario o contraseña incorrectos", response.getBody());
	}
}*/
