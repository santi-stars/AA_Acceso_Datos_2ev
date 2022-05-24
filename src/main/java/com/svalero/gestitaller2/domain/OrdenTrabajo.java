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
@Entity(name = "orden_trabajo")
@Table(name = "orden_trabajo")
public class OrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public boolean ejecutada;
    @Column(name = "fecha_orden")
    public LocalDate fechaOrden;
    @ManyToOne
    @JoinColumn(name = "id_moto")
    public Moto moto;
    @ManyToOne
    @JoinColumn(name = "id_mecanico")
    public Mecanico mecanico;
    @OneToMany(mappedBy = "recambio")
    @JsonBackReference(value = "orden_trabajo-factura")
    private List<Factura> facturas;
}
