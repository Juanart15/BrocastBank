package com.brocast.demo.Services;

import com.brocast.demo.JPA.CuentaJPA;
import com.brocast.demo.JPA.DepositoJPA;
import com.brocast.demo.ORM.CuentaORM;
import com.brocast.demo.ORM.DepositoORM;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepositoService {
    private final CuentaJPA cuentaJPA;
    private final DepositoJPA depositoJPA;

    public boolean guardarDepositos(Long numeroCuentaDeposito, Double saldoDeposito, String claveCuentaDeposito) {
        CuentaORM cuentaORM = cuentaJPA.findByCuentaNumero(numeroCuentaDeposito);
        if (cuentaORM == null) {
            throw new RuntimeException("Cuenta no encontrada");
        }

        if (!cuentaORM.getCuentaClave().equals(claveCuentaDeposito)) {
            throw new RuntimeException("Clave incorrecta para la cuenta");
        }
        DepositoORM depositoORM = new DepositoORM();
        depositoORM.setNumeroCuentaDeposito(cuentaORM.getCuentaNumero());
        depositoORM.setSaldoDeposito(saldoDeposito);
        depositoORM.setClaveCuentaDeposito(claveCuentaDeposito);

        depositoJPA.save(depositoORM);
        return true;
    }

}
