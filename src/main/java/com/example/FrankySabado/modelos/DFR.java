package com.example.FrankySabado.modelos;

import jakarta.persistence.*;

@Entity
@Table(name= "Alumnos")
public class DFR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre", nullable = false, unique = false)
    private String nombre;
    @Column(name = "direccion", nullable = false, unique = false)
    private String direccion;

    

    private Maestro;


}
