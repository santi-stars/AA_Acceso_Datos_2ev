package com.svalero.gestitaller2.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SpareDTO {

    private String description;
    private float price;
    private int stockAmount;
    private int minAmount;
    private LocalDate entryDate;
}
