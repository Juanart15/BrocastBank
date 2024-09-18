package com.brocast.demo.JPA;

import com.brocast.demo.ORM.ClienteORM;
import com.brocast.demo.ORM.CuentaORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaJPA extends JpaRepository<CuentaORM, Long> {
    CuentaORM findByClienteCedula(Long clienteCedula);
    CuentaORM findByCuentaNumero(Long numeroCuenta);
    CuentaORM findByClienteCedulaAndCuentaClave(Long clienteCedula, String cuentaClave);
}

