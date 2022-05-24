package com.svalero.gestitaller2.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RecambioDTO {

    private String descripcion;
    private float precio;
    private int cantidadStock;
    private int cantidadMinima;
    private LocalDate fechaEntrada;
}
