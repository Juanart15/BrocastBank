package com.brocast.demo.Controller;

import com.brocast.demo.DTO.CuentaDTO;
import com.brocast.demo.ORM.CuentaORM;
import com.brocast.demo.Services.CuentaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://212.90.120.186")
public class CuentaController {

    public CuentaService cuentaService;

    @CrossOrigin
    @PostMapping(path = "/cuenta")
    public ResponseEntity<String> guardarCuenta(@RequestBody CuentaDTO cuentaDTO) {
        try {
            cuentaService.guardarCuenta(cuentaDTO.clienteCedula(), cuentaDTO.cuentaSaldo(), cuentaDTO.cuentaClave());
            return new ResponseEntity<>("Cuenta guardada", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al crear la cuenta: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/cuentas")
    public CuentaORM mostrarCuenta(@RequestParam Long clienteCedula) {
        return cuentaService.consultarCuenta(clienteCedula);
    }
    @GetMapping(path = "/cuent")
    public ResponseEntity<CuentaORM> consultarCuenta(@RequestParam Long clienteCedula, @RequestParam String cuentaClave) {
        try {
            CuentaORM cuenta = cuentaService.consultarCuenta(clienteCedula, cuentaClave);
            return ResponseEntity.ok(cuenta);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}



