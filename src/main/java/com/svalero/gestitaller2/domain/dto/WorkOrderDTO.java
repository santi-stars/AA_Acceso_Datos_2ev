package com.svalero.gestitaller2.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkOrderDTO {

    private boolean executed;
    private LocalDate orderDate;
    //private long invoice;
    private long bike;
    private long mechanic;
}
