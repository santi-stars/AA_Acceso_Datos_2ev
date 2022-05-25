package com.svalero.gestitaller2.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InvoiceDTO {

    private LocalDate invoiceDate;
    private long spare;
    private int amount;
    private boolean paid;
    private long client;
    private long bike;
    private long workOrder;
}
