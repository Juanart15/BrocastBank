package com.brocast.demo.Controller;

import com.brocast.demo.DTO.ClienteDTO;
import com.brocast.demo.JPA.ClienteJPA;
import com.brocast.demo.ORM.ClienteORM;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ClienteController {

    private ClienteJPA jpa;
    List<ClienteDTO> clientes = new ArrayList<>();

    @PostMapping(path = "/cliente")
    public ResponseEntity<String> guardarCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteORM clienteORM = new ClienteORM();
            clienteORM.setNombre(clienteDTO.nombre());
            clienteORM.setCedula(clienteDTO.cedula());
            clienteORM.setTelefono(clienteDTO.telefono());

            jpa.save(clienteORM);
            return new ResponseEntity<>("Cliente guardado", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();  // Registrar el error en la consola o logs
            return new ResponseEntity<>("Error al guardar cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
