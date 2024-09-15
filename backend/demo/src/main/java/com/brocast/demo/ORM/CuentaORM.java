package com.brocast.demo.ORM;

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
    private Long idCuenta;

    @Column
    private Long numeroCuenta;

    @ManyToOne
    @JoinColumn(name = "cedulaCliente", referencedColumnName = "cedula")
    private ClienteORM cliente;

    @Column
    private Long telefonoCliente;

    @Column
    private Double saldo;

    @Column
    private LocalDate fechaCreacion;

    @Column
    private String clave;


}

