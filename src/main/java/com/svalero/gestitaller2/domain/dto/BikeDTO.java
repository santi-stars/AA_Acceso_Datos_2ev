package com.svalero.gestitaller2.domain.dto;

import com.svalero.gestitaller2.domain.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BikeDTO {

    private String brand;
    private String model;
    private String licensePlate;
    private byte[] bikeImage;
    private long client;
}
