package com.brocast.demo.unitarias;

import com.brocast.demo.dto.AuditoriaDTO;
import com.brocast.demo.jpa.AuditoriaJPA;
import com.brocast.demo.jpa.CuentaJPA;
import com.brocast.demo.jpa.DepositoJPA;
import com.brocast.demo.orm.CuentaORM;
import com.brocast.demo.orm.DepositoORM;
import com.brocast.demo.publicador.AuditoriaPublisher;
import com.brocast.demo.services.DepositoService;
import com.brocast.demo.services.excepciones.ClaveIncorrectaException;
import com.brocast.demo.services.excepciones.CuentaNoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TestDepositoService {

    @Mock
    private CuentaJPA cuentaJPA;
    @Mock
    private DepositoJPA depositoJPA;
    @Mock
    private AuditoriaJPA auditoriaJPA;
    @Mock
    private AuditoriaPublisher auditoriaPublisher;

    @InjectMocks
    private DepositoService depositoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarDepositoExitoso() {

        CuentaORM cuenta = new CuentaORM();
        cuenta.setCuentaNumero(12345678L);
        cuenta.setCuentaSaldo(500.0);
        cuenta.setCuentaClave("clave123");
        cuenta.setClienteCedula(Long.valueOf("1234567890"));
        cuenta.setClienteNombre("Test User");

        when(cuentaJPA.findByCuentaNumero(12345678L)).thenReturn(cuenta);

        doNothing().when(auditoriaPublisher).send(any(AuditoriaDTO.class));


        boolean resultado = depositoService.guardarDepositos(12345678L, 100.0, "clave123");


        ArgumentCaptor<DepositoORM> depositoCaptor = ArgumentCaptor.forClass(DepositoORM.class);
        verify(depositoJPA).save(depositoCaptor.capture());

        DepositoORM depositoCapturado = depositoCaptor.getValue();
        assertEquals(12345678L, depositoCapturado.getNumeroCuentaDeposito());
        assertEquals(100.0, depositoCapturado.getSaldoDeposito());
        assertEquals("clave123", depositoCapturado.getClaveCuentaDeposito());

        verify(cuentaJPA).save(cuenta);
        assertEquals(600.0, cuenta.getCuentaSaldo());

        verify(auditoriaPublisher).send(any(AuditoriaDTO.class));
        verify(auditoriaJPA).save(any());

        assertTrue(resultado);
    }


    @Test
    void testGuardarDepositoCuentaNoEncontrada() {
        when(cuentaJPA.findByCuentaNumero(12345678L)).thenReturn(null);

        assertThrows(CuentaNoEncontradaException.class, () -> {
            depositoService.guardarDepositos(12345678L, 100.0, "clave123");
        });
    }

    @Test
    void testGuardarDepositoClaveIncorrecta() {
        CuentaORM cuenta = new CuentaORM();
        cuenta.setCuentaNumero(12345678L);
        cuenta.setCuentaClave("claveIncorrecta");

        when(cuentaJPA.findByCuentaNumero(12345678L)).thenReturn(cuenta);

        assertThrows(ClaveIncorrectaException.class, () -> {
            depositoService.guardarDepositos(12345678L, 100.0, "clave123");
        });
    }

    @Test
    void testActualizacionDeSaldoDeCuenta() {

        CuentaORM cuenta = new CuentaORM();
        cuenta.setCuentaNumero(12345678L);
        cuenta.setCuentaSaldo(500.0);
        cuenta.setCuentaClave("clave123");
        cuenta.setClienteCedula(Long.valueOf("1234567890"));
        cuenta.setClienteNombre("Test User");

        when(cuentaJPA.findByCuentaNumero(12345678L)).thenReturn(cuenta);
        doNothing().when(auditoriaPublisher).send(any(AuditoriaDTO.class));

        boolean resultado = depositoService.guardarDepositos(12345678L, 100.0, "clave123");


        verify(cuentaJPA).save(cuenta);
        assertEquals(600.0, cuenta.getCuentaSaldo());
        assertTrue(resultado);
    }


    @Test
    void testEnvioDeEventoDeAuditoria() {
        // Arrange
        CuentaORM cuenta = new CuentaORM();
        cuenta.setCuentaNumero(12345678L);
        cuenta.setCuentaSaldo(500.0);
        cuenta.setCuentaClave("clave123");
        cuenta.setClienteCedula(Long.valueOf("1234567890"));
        cuenta.setClienteNombre("Test User");

        when(cuentaJPA.findByCuentaNumero(12345678L)).thenReturn(cuenta);
        doNothing().when(auditoriaPublisher).send(any(AuditoriaDTO.class));

        boolean resultado = depositoService.guardarDepositos(12345678L, 100.0, "clave123");

        ArgumentCaptor<AuditoriaDTO> auditoriaDTOCaptor = ArgumentCaptor.forClass(AuditoriaDTO.class);
        verify(auditoriaPublisher).send(auditoriaDTOCaptor.capture());

        AuditoriaDTO auditoriaDTOCapturada = auditoriaDTOCaptor.getValue();
        assertEquals(12345678L, auditoriaDTOCapturada.cuentaNumero());
        assertEquals(Long.valueOf("1234567890"), auditoriaDTOCapturada.clienteCedula());
        assertEquals("Test User", auditoriaDTOCapturada.clienteNombre());
        assertEquals(100.0, auditoriaDTOCapturada.montoDepositado());
        assertEquals(600.0, auditoriaDTOCapturada.nuevoSaldo());
        assertNotNull(auditoriaDTOCapturada.fechaHora());
        assertTrue(resultado);
    }
}