package com.brocast.demo.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "cuenta")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuentaId;

    @Column(name = "cuenta_numero", unique = true, nullable = false)
    private Long cuentaNumero;

    @Column(name = "cliente_cedula")
    private Long clienteCedula;

    @Column(name = "cliente_nombre")
    private String clienteNombre;

    @Column(name = "cliente_telefono")
    private Long clienteTelefono;

    @Column(name = "cuenta_saldo", nullable = true)
    private Double cuentaSaldo;

    @Column(name = "cuenta_fecha_creacion")
    private LocalDate cuentaFechaCreacion;

    @Column(name = "cuenta_clave")
    private String cuentaClave;
}


