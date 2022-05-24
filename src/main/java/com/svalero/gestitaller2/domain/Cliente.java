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
@Entity(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public String nombre;
    @Column
    public String apellido;
    @Column
    public int edad;
    @Column
    public String dni;
    @Column
    public String direccion;
    @Column(name = "cliente_vip")
    public boolean clienteVip;
    @OneToMany(mappedBy = "cliente")
    @JsonBackReference(value = "cliente-moto")
    private List<Moto> motos;
    @OneToMany(mappedBy = "cliente")
    @JsonBackReference(value = "cliente-factura")
    private List<Factura> facturas;

}
