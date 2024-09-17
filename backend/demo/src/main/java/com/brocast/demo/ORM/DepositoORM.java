package com.brocast.demo.ORM;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "deposito")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositoORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositoId;

    @Column(unique = true, nullable = false)
    private Long numeroCuentaDeposito;

    @Column(nullable = false)
    private Double saldoDeposito;

    @Column(unique = true, nullable = false)
    private String claveCuentaDeposito;
}
