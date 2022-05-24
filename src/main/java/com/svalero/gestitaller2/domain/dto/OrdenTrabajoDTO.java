package com.svalero.gestitaller2.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrdenTrabajoDTO {

    private boolean ejecutada;
    private LocalDate fechaOrden;
    //private long factura;
    private long moto;
    private long mecanico;
}
