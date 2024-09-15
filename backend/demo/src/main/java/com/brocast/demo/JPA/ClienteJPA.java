package com.brocast.demo.JPA;

import com.brocast.demo.ORM.ClienteORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ClienteJPA extends JpaRepository<ClienteORM, Long> {
}
