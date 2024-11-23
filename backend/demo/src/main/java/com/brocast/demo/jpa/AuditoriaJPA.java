package com.brocast.demo.jpa;

import com.brocast.demo.orm.AuditoriaORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaJPA extends JpaRepository<AuditoriaORM, Long> {
}
