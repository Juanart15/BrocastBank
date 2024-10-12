package com.brocast.unitarias;

import com.brocast.demo.JPA.CuentaJPA;
import com.brocast.demo.JPA.DepositoJPA;
import com.brocast.demo.ORM.CuentaORM;
import com.brocast.demo.ORM.DepositoORM;
import com.brocast.demo.Services.DepositoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestDepositoService {

    @Mock
    private CuentaJPA cuentaJPA;

    @Mock
    private DepositoJPA depositoJPA;

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

        when(cuentaJPA.findByCuentaNumero(12345678L)).thenReturn(cuenta);

        boolean resultado = depositoService.guardarDepositos(12345678L, 100.0, "clave123");

        ArgumentCaptor<DepositoORM> depositoCaptor = ArgumentCaptor.forClass(DepositoORM.class);
        verify(depositoJPA, times(1)).save(depositoCaptor.capture());

        DepositoORM depositoCapturado = depositoCaptor.getValue();
        assertEquals(12345678L, depositoCapturado.getNumeroCuentaDeposito());
        assertEquals(100.0, depositoCapturado.getSaldoDeposito());
        assertEquals("clave123", depositoCapturado.getClaveCuentaDeposito());

        verify(cuentaJPA, times(1)).save(cuenta);
        assertEquals(600.0, cuenta.getCuentaSaldo());
        assertTrue(resultado);
    }

    @Test
    void testGuardarDepositoCuentaNoEncontrada() {
        when(cuentaJPA.findByCuentaNumero(12345678L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            depositoService.guardarDepositos(12345678L, 100.0, "clave123");
        });
    }

    @Test
    void testGuardarDepositoClaveIncorrecta() {
        CuentaORM cuenta = new CuentaORM();
        cuenta.setCuentaNumero(12345678L);
        cuenta.setCuentaClave("claveIncorrecta");

        when(cuentaJPA.findByCuentaNumero(12345678L)).thenReturn(cuenta);

        assertThrows(RuntimeException.class, () -> {
            depositoService.guardarDepositos(12345678L, 100.0, "clave123");
        });
    }
}
