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
    public long id;
    @Column
    @NotBlank
    public String name;
    @Column
    @NotBlank
    public String surname;
    @Column
    @NotBlank
    public String dni;
    @Column(name = "vip_client")
    public boolean vip;
    @Column
    @NotNull
    private float latitude;
    @Column
    @NotNull
    private float longitud;
    @Column
    private byte[] clientImage;
    @OneToMany(mappedBy = "client")
    @JsonBackReference(value = "client-bike")
    private List<Bike> bikes;

}
