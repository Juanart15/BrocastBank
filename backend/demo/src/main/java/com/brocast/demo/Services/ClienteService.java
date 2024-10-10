package com.brocast.demo.Services;

import com.brocast.demo.JPA.ClienteJPA;
import com.brocast.demo.ORM.ClienteORM;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteJPA clienteJPA;
    
    public void guardarCliente(String nombre, Long cedula, Long telefono, String clave) {
        ClienteORM nuevoCliente = new ClienteORM(null, nombre, cedula, telefono, clave);
        clienteJPA.save(nuevoCliente);
    }
    public ClienteORM consultarCliente(Long cedula) {
        return clienteJPA.findByCedula(cedula);
    }

    public boolean validarCredenciales(String nombre, String clave) {
        ClienteORM cliente = clienteJPA.findByNombre(nombre);
        return cliente != null && cliente.getClave().equals(clave);
    }
}
