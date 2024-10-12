package com.brocast.demo.Services;

import com.brocast.demo.JPA.CuentaJPA;
import com.brocast.demo.JPA.DepositoJPA;
import com.brocast.demo.ORM.CuentaORM;
import com.brocast.demo.ORM.DepositoORM;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
public class DepositoService {
    private final CuentaJPA cuentaJPA;
    private final DepositoJPA depositoJPA;

    @Transactional
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

        cuentaORM.setCuentaSaldo(cuentaORM.getCuentaSaldo() + saldoDeposito);
        cuentaJPA.save(cuentaORM); 

        return true;
    }
}
