package com.brocast.demo.services;


import com.brocast.demo.jpa.ClienteJPA;
import com.brocast.demo.jpa.CuentaJPA;
import com.brocast.demo.orm.ClienteORM;
import com.brocast.demo.orm.CuentaORM;
import com.brocast.demo.services.excepciones.ClienteNotFoundException;
import com.brocast.demo.services.excepciones.CuentaIncorrectaException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Random;

@Service
@AllArgsConstructor
public class CuentaService {
    private static final Random RANDOM = new Random();
    private static final Logger logger = LoggerFactory.getLogger(CuentaService.class);
    private final CuentaJPA cuentaJPA;
    private final ClienteJPA clienteJPA;

    public boolean guardarCuenta(Long cedulaCliente, Double cuentaSaldo, String cuentaClave) {
        logger.info("Buscando cliente con cédula: {}", cedulaCliente);

        ClienteORM clienteORM = clienteJPA.findByCedula(cedulaCliente);
        if (clienteORM == null) {
            throw new ClienteNotFoundException("Cliente no encontrado");
        }

        Long numeroDeCuenta = 1000000000L + (RANDOM.nextLong() & Long.MAX_VALUE) % 9000000000L;
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
        return cuentaJPA.findByClienteCedula(clienteCedula);
    }

    public CuentaORM consultarCuenta(Long clienteCedula, String cuentaClave) {
        CuentaORM cuenta = cuentaJPA.findByClienteCedulaAndCuentaClave(clienteCedula, cuentaClave);
        if (cuenta == null) {
            throw new CuentaIncorrectaException("Cédula o clave incorrecta.");
        }
        return cuenta;
    }
}


