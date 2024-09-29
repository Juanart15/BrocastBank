package com.brocast.demo.ORM;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "cuentas")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuentaId;

    @Column(unique = true, nullable = false)
    private Long cuentaNumero;

    @Column
    private Long clienteCedula;

    @Column
    private String clienteNombre;

    @Column
    private Long clienteTelefono;

    @Column(nullable = true)
    private Double cuentaSaldo;

    @Column
    private LocalDate cuentaFechaCreacion;

    @Column
    private String cuentaClave;

    public CuentaORM(Object object, long Long, double v, String clave123) {
    }

    public CuentaORM(long l, double v, String clave123) {
    }
}


