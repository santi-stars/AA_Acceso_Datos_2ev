package com.svalero.gestitaller2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public int amount;
    @Column(name = "invoice_date")
    public LocalDate invoiceDate;
    @ManyToOne
    @JoinColumn(name = "spare_id")
    public Spare spare;
    @Column
    public boolean paid;
    @ManyToOne
    @JoinColumn(name = "client_id")
    public Client client;
    @ManyToOne
    @JoinColumn(name = "bike_id")
    public Bike bike;
    @ManyToOne
    @JoinColumn(name = "order_id")
    public WorkOrder workOrder;
}
