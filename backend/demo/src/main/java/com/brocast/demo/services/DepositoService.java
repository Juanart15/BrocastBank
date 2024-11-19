package com.brocast.demo.services;


import com.brocast.demo.dto.AuditoriaDTO;
import com.brocast.demo.jpa.AuditoriaJPA;
import com.brocast.demo.jpa.CuentaJPA;
import com.brocast.demo.jpa.DepositoJPA;
import com.brocast.demo.orm.AuditoriaORM;
import com.brocast.demo.orm.CuentaORM;
import com.brocast.demo.orm.DepositoORM;
import com.brocast.demo.publicador.AuditoriaPublisher;
import com.brocast.demo.services.excepciones.ClaveIncorrectaException;
import com.brocast.demo.services.excepciones.CuentaNoEncontradaException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class DepositoService {
    private final CuentaJPA cuentaJPA;
    private final DepositoJPA depositoJPA;
    private final AuditoriaJPA auditoriaJPA;

    @Autowired
    private AuditoriaPublisher auditoriaPublisher;


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

        AuditoriaDTO eventoDeposito = new AuditoriaDTO(
                cuentaORM.getCuentaNumero(),
                cuentaORM.getClienteCedula(),
                cuentaORM.getClienteNombre(),
                depositoORM.getSaldoDeposito(),
                cuentaORM.getCuentaSaldo(),
                LocalDateTime.now()
        );

        auditoriaPublisher.send(eventoDeposito);

        AuditoriaORM auditoriaORM = new AuditoriaORM();
        auditoriaORM.setCuentaNumeroAuditoria(cuentaORM.getCuentaNumero());
        auditoriaORM.setClienteCedulaAuditoria(cuentaORM.getClienteCedula());
        auditoriaORM.setClienteNombreAuditoria(cuentaORM.getClienteNombre());
        auditoriaORM.setMontoDepositadoAuditoria(saldoDeposito);
        auditoriaORM.setNuevoSaldoAuditoria(cuentaORM.getCuentaSaldo());
        auditoriaORM.setFechaHoraAuditoria(LocalDateTime.now());

        auditoriaJPA.save(auditoriaORM);


        return true;
    }
}