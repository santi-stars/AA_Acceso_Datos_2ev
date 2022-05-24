package com.svalero.gestitaller2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public int cantidad;
    @Column(name = "fecha_factura")
    public LocalDate fechaFactura;
    @ManyToOne
    @JoinColumn(name = "id_recambio")
    public Recambio recambio;
    @Column
    public boolean pagada;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    public Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_moto")
    public Moto moto;
    @ManyToOne
    @JoinColumn(name = "id_orden")
    public OrdenTrabajo ordenTrabajo;
}
