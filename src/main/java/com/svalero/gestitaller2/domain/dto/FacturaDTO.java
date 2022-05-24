package com.svalero.gestitaller2.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FacturaDTO {

    private LocalDate fechaFactura;
    private long recambio;
    private int cantidad;
    private boolean pagada;
    private long cliente;
    private long moto;
    private long ordenTrabajo;
}
