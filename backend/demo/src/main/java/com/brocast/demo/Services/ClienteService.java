package com.brocast.demo.Services;

import com.brocast.demo.JPA.ClienteJPA;
import com.brocast.demo.ORM.ClienteORM;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {
    public ClienteJPA clienteJPA;

    public boolean guardarCliente(String nombre, Long cedula, Long telefono, String clave) {
        ClienteORM clienteORM = new ClienteORM();
        clienteORM.setNombre(nombre);
        clienteORM.setCedula(cedula);
        clienteORM.setTelefono(telefono);
        clienteORM.setClave(clave);
        clienteJPA.save(clienteORM);
        return true;
    }
    public ClienteORM consultarCliente(Long cedula) {
        ClienteORM list = clienteJPA.findByCedula(cedula);
        return list;
    }

}
