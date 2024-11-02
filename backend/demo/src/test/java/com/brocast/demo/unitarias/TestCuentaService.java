package com.brocast.demo.unitarias;

import com.brocast.demo.jpa.ClienteJPA;
import com.brocast.demo.jpa.CuentaJPA;
import com.brocast.demo.orm.ClienteORM;
import com.brocast.demo.orm.CuentaORM;
import com.brocast.demo.services.excepciones.ClienteNotFoundException;
import com.brocast.demo.services.CuentaService;
import com.brocast.demo.services.excepciones.CuentaIncorrectaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestCuentaService {
    @Mock
    private CuentaJPA cuentaJPA;

    @Mock
    private ClienteJPA clienteJPA;

    @InjectMocks
    private CuentaService cuentaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarCuenta() {
        ClienteORM cliente = new ClienteORM(1L, "Juan", 12345678L, 98765432L, "clave123");
        when(clienteJPA.findByCedula(12345678L)).thenReturn(cliente);

        boolean resultado = cuentaService.guardarCuenta(12345678L, 1000.0, "claveCuenta");

        ArgumentCaptor<CuentaORM> cuentaCaptor = ArgumentCaptor.forClass(CuentaORM.class);
        verify(cuentaJPA, times(1)).save(cuentaCaptor.capture());

        CuentaORM cuentaCapturada = cuentaCaptor.getValue();
        assertEquals(cliente.getNombre(), cuentaCapturada.getClienteNombre());
        assertEquals(cliente.getCedula(), cuentaCapturada.getClienteCedula());
        assertEquals(1000.0, cuentaCapturada.getCuentaSaldo());
        assertEquals("claveCuenta", cuentaCapturada.getCuentaClave());
        assertNotNull(cuentaCapturada.getCuentaNumero());
        assertEquals(LocalDate.now(), cuentaCapturada.getCuentaFechaCreacion());
        assertTrue(resultado);
    }

    @Test
    void testGuardarCuentaClienteNoEncontrado() {
        when(clienteJPA.findByCedula(12345678L)).thenReturn(null);

        assertThrows(ClienteNotFoundException.class, () -> {
            cuentaService.guardarCuenta(12345678L, 1000.0, "claveCuenta");
        });
    }

    @Test
    void testConsultarCuentaPorCedula() {
        CuentaORM cuenta = new CuentaORM();
        cuenta.setClienteCedula(12345678L);
        when(cuentaJPA.findByClienteCedula(12345678L)).thenReturn(cuenta);

        CuentaORM resultado = cuentaService.consultarCuenta(12345678L);

        assertNotNull(resultado);
        assertEquals(12345678L, resultado.getClienteCedula());
    }

    @Test
    void testConsultarCuentaPorCedulaYClaveCorrecta() {
        CuentaORM cuenta = new CuentaORM();
        cuenta.setClienteCedula(12345678L);
        cuenta.setCuentaClave("claveCuenta");
        when(cuentaJPA.findByClienteCedulaAndCuentaClave(12345678L, "claveCuenta")).thenReturn(cuenta);

        CuentaORM resultado = cuentaService.consultarCuenta(12345678L, "claveCuenta");

        assertNotNull(resultado);
        assertEquals(12345678L, resultado.getClienteCedula());
        assertEquals("claveCuenta", resultado.getCuentaClave());
    }

    @Test
    void testConsultarCuentaPorCedulaYClaveIncorrecta() {
        when(cuentaJPA.findByClienteCedulaAndCuentaClave(12345678L, "claveIncorrecta")).thenReturn(null);

        assertThrows(CuentaIncorrectaException.class, () -> {
            cuentaService.consultarCuenta(12345678L, "claveIncorrecta");
        });
    }
}
