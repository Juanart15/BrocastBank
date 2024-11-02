package com.brocast.demo.services;

import com.brocast.demo.jpa.CuentaJPA;
import com.brocast.demo.jpa.DepositoJPA;
import com.brocast.demo.orm.CuentaORM;
import com.brocast.demo.orm.DepositoORM;
import com.brocast.demo.services.excepciones.ClaveIncorrectaException;
import com.brocast.demo.services.excepciones.CuentaNoEncontradaException;

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
            throw new CuentaNoEncontradaException("Cuenta no encontrada para el número: " + numeroCuentaDeposito);
        }

        if (!cuentaORM.getCuentaClave().equals(claveCuentaDeposito)) {
            throw new ClaveIncorrectaException("Clave incorrecta para la cuenta: " + numeroCuentaDeposito);
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
