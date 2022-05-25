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
@Entity(name = "spare")
public class Spare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public String description;
    @Column
    public float price;
    @Column(name = "stock_amount")
    public int stockAmount;
    @Column(name = "min_amount")
    public int minAmount;
    @Column(name = "entry_date")
    public LocalDate entryDate;
    @OneToMany(mappedBy = "spare")
    @JsonBackReference(value = "spare-invoice")
    private List<Invoice> invoices;
}
