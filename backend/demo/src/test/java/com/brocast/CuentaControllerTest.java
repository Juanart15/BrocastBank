/*package com.brocast;

import com.brocast.demo.Controller.CuentaController;
import com.brocast.demo.DTO.CuentaDTO;
import com.brocast.demo.ORM.CuentaORM;
import com.brocast.demo.Services.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CuentaControllerTest {

	@Mock
	private CuentaService cuentaService;

	@InjectMocks
	private CuentaController cuentaController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGuardarCuenta_Success() {
		CuentaDTO cuentaDTO = new CuentaDTO(123L, 1000.0, "clave123");
		doNothing().when(cuentaService).guardarCuenta(anyLong(), anyDouble(), anyString());

		ResponseEntity<String> response = cuentaController.guardarCuenta(cuentaDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Cuenta guardada", response.getBody());
		verify(cuentaService, times(1)).guardarCuenta(123L, 1000.0, "clave123");
	}

	@Test
	public void testGuardarCuenta_Exception() {
		CuentaDTO cuentaDTO = new CuentaDTO(123L, 1000.0, "clave123");
		doThrow(new RuntimeException("Error")).when(cuentaService).guardarCuenta(anyLong(), anyDouble(), anyString());

		ResponseEntity<String> response = cuentaController.guardarCuenta(cuentaDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getBody().contains("Error al crear la cuenta"));
		verify(cuentaService, times(1)).guardarCuenta(123L, 1000.0, "clave123");
	}

	@Test
	public void testConsultarCuenta_Success() {
		CuentaORM cuentaORM = new CuentaORM(123L, 1000.0, "clave123");
		when(cuentaService.consultarCuenta(anyLong())).thenReturn(cuentaORM);

		CuentaORM result = cuentaController.mostrarCuenta(123L);

		assertEquals(123L, result.getClienteCedula());
		verify(cuentaService, times(1)).consultarCuenta(123L);
	}

	@Test
	public void testConsultarCuenta_ClaveInvalida() {
		when(cuentaService.consultarCuenta(123L, "clave123")).thenThrow(new RuntimeException("Clave inválida"));

		ResponseEntity<CuentaORM> response = cuentaController.consultarCuenta(123L, "clave123");

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		verify(cuentaService, times(1)).consultarCuenta(123L, "clave123");
	}
}*/
