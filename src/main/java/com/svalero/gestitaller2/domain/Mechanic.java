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
@Entity(name = "mechanic")
public class Mechanic {

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
    public boolean available;
    @Column(name = "hiring_date")
    public LocalDate hiringDate;
    @OneToMany(mappedBy = "mechanic")
    @JsonBackReference(value = "mechanic-work_order")
    private List<WorkOrder> workOrders;
}
