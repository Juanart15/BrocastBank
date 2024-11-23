package com.brocast.demo.jpa;

import com.brocast.demo.orm.ClienteORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteJPA extends JpaRepository<ClienteORM, Long> {
    ClienteORM findByCedula(Long cedula);
    ClienteORM findByNombre(String nombre);
}
