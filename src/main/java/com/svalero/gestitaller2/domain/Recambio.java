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
@Entity(name = "recambio")
public class Recambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public String descripcion;
    @Column
    public float precio;
    @Column(name = "cantidad_stock")
    public int cantidadStock;
    @Column(name = "cantidad_minima")
    public int cantidadMinima;
    @Column(name = "fecha_entrada")
    public LocalDate fechaEntrada;
    @OneToMany(mappedBy = "recambio")
    @JsonBackReference(value = "recambio-factura")
    private List<Factura> facturas;
}
