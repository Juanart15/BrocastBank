package com.brocast.demo.JPA;

import com.brocast.demo.ORM.DepositoORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoJPA extends JpaRepository<DepositoORM, Long> {
}
