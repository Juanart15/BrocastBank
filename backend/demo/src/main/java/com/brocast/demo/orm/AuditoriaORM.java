package com.brocast.demo.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "auditorias")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuditoria;

    @Column(name ="cuenta_numero_auditoria" ,nullable = false)
    private Long cuentaNumeroAuditoria;

    @Column(name ="cliente_cedula_auditoria" ,nullable = false)
    private Long clienteCedulaAuditoria;

    @Column(name ="cliente_nombre_auditoria" ,nullable = false)
    private String clienteNombreAuditoria;

    @Column(name ="monto_depositado_auditoria" ,nullable = false)
    private Double montoDepositadoAuditoria;

    @Column(name ="nuevo_saldo_auditoria" ,nullable = false)
    private Double nuevoSaldoAuditoria;

    @Column(name ="fecha_hora_auditoria" ,nullable = false)
    private LocalDateTime fechaHoraAuditoria;

}
