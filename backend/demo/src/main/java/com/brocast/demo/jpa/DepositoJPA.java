package com.brocast.demo.jpa;

import com.brocast.demo.orm.DepositoORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoJPA extends JpaRepository<DepositoORM, Long> {
}
