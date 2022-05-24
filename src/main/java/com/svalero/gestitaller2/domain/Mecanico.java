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
@Entity(name = "mecanico")
public class Mecanico {

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
    public boolean disponible;
    @Column(name = "fecha_contratacion")
    public LocalDate fechaContratacion;
    @OneToMany(mappedBy = "mecanico")
    @JsonBackReference(value = "mecanico-orden_trabajo")
    private List<OrdenTrabajo> ordenTrabajos;
}
