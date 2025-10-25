package com.example.FrankySabado.modelos.dtos;

import com.example.FrankySabado.ayudas.Roles;

public class DocenteDTO {
    private Integer id;
    private String nombre;
    private String especialidad;
    private Roles rol; // Si quieres mostrar su rol como usuario asociado

    public DocenteDTO() {}

    public DocenteDTO(Integer id, String nombre, String especialidad, Roles rol) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.rol = rol;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public Roles getRol() { return rol; }
    public void setRol(Roles rol) { this.rol = rol; }
}
