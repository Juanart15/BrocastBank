package com.brocast.demo.Controller;

import com.brocast.demo.DTO.ClienteDTO;
import com.brocast.demo.JPA.ClienteJPA;
import com.brocast.demo.ORM.ClienteORM;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ClienteController {

    private ClienteJPA jpa;

    @PostMapping(path = "/cliente")
    public ResponseEntity<String> guardarCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteORM clienteORM = new ClienteORM();
            clienteORM.setNombre(clienteDTO.nombre());
            clienteORM.setCedula(clienteDTO.cedula());
            clienteORM.setTelefono(clienteDTO.telefono());
            clienteORM.setClave(clienteDTO.clave());

            jpa.save(clienteORM);
            return new ResponseEntity<>("Cliente guardado", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al guardar cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "eliminarCliente")
    public ResponseEntity<String> eliminarCliente(@RequestBody ClienteDTO clienteDTO) {
        if (clienteDTO.cedula() == null) {
            return new ResponseEntity<>("La cédula no puede ser nula", HttpStatus.BAD_REQUEST);
        }

        Optional<ClienteORM> clienteORMOptional = Optional.ofNullable(jpa.findByCedula(clienteDTO.cedula()));

        if (clienteORMOptional.isEmpty()) {
            return new ResponseEntity<>("El cliente no existe", HttpStatus.NOT_FOUND);
        }

        jpa.delete(clienteORMOptional.get());
        return new ResponseEntity<>("Cliente eliminado", HttpStatus.OK);
    }

}
