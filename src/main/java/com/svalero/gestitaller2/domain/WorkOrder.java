package com.svalero.gestitaller2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "work_order")
@Table(name = "work_order")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public boolean executed;
    @Column(name = "order_date")
    public LocalDate orderDate;
    @ManyToOne
    @JoinColumn(name = "bike_id")
    public Bike bike;
    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    public Mechanic mechanic;
    @OneToMany(mappedBy = "spare")
    @JsonBackReference(value = "work_order-invoice")
    private List<Invoice> invoices;
}
