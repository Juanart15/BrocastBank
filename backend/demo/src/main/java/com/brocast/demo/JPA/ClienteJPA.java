package com.brocast.demo.JPA;

import com.brocast.demo.ORM.ClienteORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClienteJPA extends JpaRepository<ClienteORM, Long> {
    ClienteORM findByCedula(Long cedula);
}
