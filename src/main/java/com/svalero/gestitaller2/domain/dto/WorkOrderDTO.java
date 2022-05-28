package com.svalero.gestitaller2.domain.dto;

import com.svalero.gestitaller2.domain.Bike;
import com.svalero.gestitaller2.domain.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkOrderDTO {

    private LocalDate orderDate;
    private String description;
    public long client;
    private long bike;
}
