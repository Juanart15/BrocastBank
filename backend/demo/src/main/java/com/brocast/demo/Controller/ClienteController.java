package com.brocast.demo.Controller;

import com.brocast.demo.DTO.ClienteDTO;
import com.brocast.demo.ORM.ClienteORM;
import com.brocast.demo.Services.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://212.90.120.186")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping(path = "/cliente")
    @CrossOrigin
    public ResponseEntity<String> guardarCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.guardarCliente(clienteDTO.nombre(), clienteDTO.cedula(), clienteDTO.telefono(), clienteDTO.clave());
            return new ResponseEntity<>("Cliente guardado", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al guardar cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/clientes")
    public ClienteORM mostrarCliente(@RequestParam Long cedula) {
        return clienteService.consultarCliente(cedula);
    }
    @GetMapping(path = "/client")
    @CrossOrigin
    public ResponseEntity<ClienteORM> obtenerClientePorCedula(@RequestParam Long cedula) {
        ClienteORM cliente = clienteService.consultarCliente(cedula);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody ClienteDTO clienteDTO) {
        boolean valid = clienteService.validarCredenciales(clienteDTO.nombre(), clienteDTO.clave());  
        if (valid) {
            return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
    }
}