package com.brocast;

import com.brocast.demo.Controller.DepositoController;
import com.brocast.demo.DTO.DepositoDTO;
import com.brocast.demo.Services.DepositoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepositoControllerTest {

	@Mock
	private DepositoService depositoService;

	@InjectMocks
	private DepositoController depositoController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGuardarDeposito_Success() {
		DepositoDTO depositoDTO = new DepositoDTO(1234567890L, 500.0, "clave123");
		doNothing().when(depositoService).guardarDepositos(anyLong(), anyDouble(), anyString());

		ResponseEntity<String> response = depositoController.guardarDeposito(depositoDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Deposito Registrado", response.getBody());
		verify(depositoService, times(1)).guardarDepositos(1234567890L, 500.0, "clave123");
	}

	@Test
	public void testGuardarDeposito_Exception() {
		DepositoDTO depositoDTO = new DepositoDTO(1234567890L, 500.0, "clave123");
		doThrow(new RuntimeException("Error al guardar")).when(depositoService).guardarDepositos(anyLong(), anyDouble(), anyString());

		ResponseEntity<String> response = depositoController.guardarDeposito(depositoDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getBody().contains("Error al guardar el deposito"));
		verify(depositoService, times(1)).guardarDepositos(1234567890L, 500.0, "clave123");
	}
}
