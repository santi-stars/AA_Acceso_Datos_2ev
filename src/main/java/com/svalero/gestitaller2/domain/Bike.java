package com.svalero.gestitaller2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bike")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public String brand;
    @Column
    public String model;
    @Column
    public String licensePlate;
    @Column
    private byte[] bikeImage;
    @ManyToOne
    @JoinColumn(name = "client_id")
    public Client client;
    @OneToMany(mappedBy = "bike")
    @JsonBackReference(value = "bike-invoice")
    private List<Invoice> invoices;
    @OneToMany(mappedBy = "bike")
    @JsonBackReference(value = "bike-work_order")
    private List<WorkOrder> workOrders;

}
