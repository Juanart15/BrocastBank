package com.brocast.demo.DTO;

import java.math.BigInteger;

public record ClienteDTO(Long id, String nombre, Long cedula, Long telefono) {
}