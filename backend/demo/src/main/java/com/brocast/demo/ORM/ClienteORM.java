package com.brocast.demo.ORM;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Table(name = "cliente")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteORM {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private Long cedula;

    @Column
    private Long telefono;


}