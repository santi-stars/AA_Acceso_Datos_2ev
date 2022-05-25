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
@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public String name;
    @Column
    public String surname;
    @Column
    public int age;
    @Column
    public String dni;
    @Column
    public String direction;
    @Column(name = "vip_client")
    public boolean vipClient;
    @OneToMany(mappedBy = "client")
    @JsonBackReference(value = "client-bike")
    private List<Bike> bikes;
    @OneToMany(mappedBy = "client")
    @JsonBackReference(value = "client-invoice")
    private List<Invoice> invoices;

}
