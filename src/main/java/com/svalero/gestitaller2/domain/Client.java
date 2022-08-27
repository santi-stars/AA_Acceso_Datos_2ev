package com.svalero.gestitaller2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String surname;
    @Column
    @NotBlank
    private String dni;
    @Column(name = "vip_client")
    private boolean vip;
    @Column
    @NotNull
    private float latitude;
    @Column
    @NotNull
    private float longitude;
    @Column
    @Lob
    private byte[] clientImage;
    @OneToMany(mappedBy = "client")
    @JsonBackReference(value = "client-bike")
    private List<Bike> bikes;
    @OneToMany(mappedBy = "client")
    @JsonBackReference(value = "client-work_order")
    private List<WorkOrder> workOrders;
}
