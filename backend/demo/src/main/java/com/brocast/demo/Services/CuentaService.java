package com.brocast.demo.Services;

import com.brocast.demo.Controller.ClienteController;
import com.brocast.demo.JPA.ClienteJPA;
import com.brocast.demo.JPA.CuentaJPA;
import com.brocast.demo.ORM.ClienteORM;
import com.brocast.demo.ORM.CuentaORM;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@AllArgsConstructor
public class CuentaService {

    private final CuentaJPA cuentaJPA;
    private final ClienteJPA clienteJPA;

    public boolean guardarCuenta(Long cedulaCliente, Double cuentaSaldo, String cuentaClave) {

        System.out.println("Buscando cliente con cédula: " + cedulaCliente);
        ClienteORM clienteORM = clienteJPA.findByCedula(cedulaCliente);

        if (clienteORM == null) {
            throw new RuntimeException("Cliente no encontrado");
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
        CuentaORM list = cuentaJPA.findByClienteCedula(clienteCedula);
        return list;
    }
}



