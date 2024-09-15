package com.brocast.demo.Controller;

import com.brocast.demo.DTO.CuentaDTO;
import com.brocast.demo.JPA.ClienteJPA;
import com.brocast.demo.JPA.CuentaJPA;
import com.brocast.demo.ORM.ClienteORM;
import com.brocast.demo.ORM.CuentaORM;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CuentaController {

    private final CuentaJPA cuentaJPA;
    private final ClienteJPA clienteJPA;

    @PostMapping(path = "/cuenta")
    public ResponseEntity<String> guardarCuenta(@RequestBody CuentaDTO cuentaDTO) {
        try {
            ClienteORM cliente = clienteJPA.findByCedula(cuentaDTO.cedulaCliente());
            if (cliente == null) {
                return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
            }

            CuentaORM cuentaORM = new CuentaORM();
            cuentaORM.setNumeroCuenta(cuentaDTO.numeroCuenta());
            cuentaORM.setCliente(cliente);
            cuentaORM.setTelefonoCliente(cuentaDTO.telefonoCLiente());
            cuentaORM.setSaldo(cuentaDTO.saldo());
            cuentaORM.setFechaCreacion(cuentaDTO.fechaCreacion());
            cuentaORM.setClave(cuentaDTO.clave());


            cuentaJPA.save(cuentaORM);
            return new ResponseEntity<>("Cuenta guardada", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al guardar cuenta: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

