package com.brocast.demo.dto;

import java.time.LocalDateTime;

public record AuditoriaDTO(
        Long cuentaNumero,
        Long clienteCedula,
        String clienteNombre,
        Double montoDepositado,
        Double nuevoSaldo,
        LocalDateTime fechaHora
){
}