package com.brocast.demo.DTO;

import java.time.LocalDate;

public record CuentaDTO(Long numeroCuenta, Long cedulaCliente,Long telefonoCLiente, Double saldo, LocalDate fechaCreacion, String clave ) {
}
