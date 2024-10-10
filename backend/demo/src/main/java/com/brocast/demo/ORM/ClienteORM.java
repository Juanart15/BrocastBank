package com.brocast.demo.ORM;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "clientes")
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

    @Column(name = "cedula", unique = true, nullable = false)
    private Long cedula;


    @Column
    private Long telefono;

    @Column
    private String clave;


}