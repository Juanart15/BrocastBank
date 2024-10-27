package com.brocast.demo.controller;

import com.brocast.demo.dto.DepositoDTO;
import com.brocast.demo.services.DepositoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://212.90.120.186")
public class DepositoController {
    private final DepositoService depositoService;

    @PostMapping(path = "/deposito")
    @CrossOrigin
    public ResponseEntity<String> guardarDeposito(@RequestBody DepositoDTO depositoDTO) {
        try {
            depositoService.guardarDepositos(depositoDTO.numeroCuentaDeposito(), depositoDTO.saldoDeposito(), depositoDTO.claveCuentaDeposito());
            return new ResponseEntity<>("Deposito Registrado", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al guardar el deposito: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}