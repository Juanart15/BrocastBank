package com.brocast.demo.Controller;
import com.brocast.demo.DTO.ClienteDTO;
import com.brocast.demo.ORM.ClienteORM;
import com.brocast.demo.Services.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ClienteController {

    public ClienteService clienteService;

    @PostMapping(path = "/cliente")
    @CrossOrigin
    public ResponseEntity<String> guardarCliente(@RequestBody ClienteDTO clienteDTO) {
        try{
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

}


