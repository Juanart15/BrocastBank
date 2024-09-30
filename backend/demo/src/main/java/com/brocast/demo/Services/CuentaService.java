package com.brocast.demo.Services;


import com.brocast.demo.JPA.ClienteJPA;
import com.brocast.demo.JPA.CuentaJPA;
import com.brocast.demo.ORM.ClienteORM;
import com.brocast.demo.ORM.CuentaORM;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@AllArgsConstructor
public class CuentaService {
    private static final Logger logger = LoggerFactory.getLogger(CuentaService.class);
    private final CuentaJPA cuentaJPA;
    private final ClienteJPA clienteJPA;

    public boolean guardarCuenta(Long cedulaCliente, Double cuentaSaldo, String cuentaClave) {
        logger.info("Buscando cliente con cédula: {}", cedulaCliente);

        ClienteORM clienteORM = clienteJPA.findByCedula(cedulaCliente);
        if (clienteORM == null) {
            throw new ClienteNotFoundException("Cliente no encontrado");
        }

        Long numeroDeCuenta = (long) (1000000000L + (Math.random() * 9000000000L));
        CuentaORM cuentaORM = new CuentaORM();
        cuentaORM.setClienteNombre(clienteORM.getNombre());
        cuentaORM.setClienteCedula(clienteORM.getCedula());
        cuentaORM.setClienteTelefono(clienteORM.getTelefono());
        cuentaORM.setCuentaClave(cuentaClave);
        cuentaORM.setCuentaNumero(numeroDeCuenta);
        cuentaORM.setCuentaSaldo(cuentaSaldo);
        cuentaORM.setCuentaFechaCreacion(LocalDate.now());
        cuentaJPA.save(cuentaORM);
        return true;
    }

    public CuentaORM consultarCuenta(Long clienteCedula) {
        CuentaORM cuenta = cuentaJPA.findByClienteCedula(clienteCedula);
        return cuenta;
    }

    public CuentaORM consultarCuenta(Long clienteCedula, String cuentaClave) {
        CuentaORM cuenta = cuentaJPA.findByClienteCedulaAndCuentaClave(clienteCedula, cuentaClave);
        if (cuenta == null) {
            throw new CuentaIncorrectaException("Cédula o clave incorrecta.");
        }
        return cuenta;
    }
}

// Excepción personalizada para cliente no encontrado
class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String message) {
        super(message);
    }
}

// Excepción personalizada para cuenta incorrecta
class CuentaIncorrectaException extends RuntimeException {
    public CuentaIncorrectaException(String message) {
        super(message);
    }
}


