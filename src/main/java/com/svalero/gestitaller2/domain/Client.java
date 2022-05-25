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
    public String dni;
    @Column(name = "vip_client")
    public boolean vip;
    @Column
    private float latitude;
    @Column
    private float longitud;
    @Column
    private byte[] clientImage;
    @Column
    public int age;
    @OneToMany(mappedBy = "client")
    @JsonBackReference(value = "client-bike")
    private List<Bike> bikes;
    @OneToMany(mappedBy = "client")
    @JsonBackReference(value = "client-invoice")
    private List<Invoice> invoices;

}
