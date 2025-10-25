package com.example.FrankySabado.modelos.dtos;

import com.example.FrankySabado.ayudas.Roles;

public class LoginResponseDTO {
    private String nombre;
    private String correo;
    private Roles rol;

    public LoginResponseDTO() {}
    public LoginResponseDTO(String nombre, String correo, Roles rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Roles getRol() { return rol; }
    public void setRol(Roles rol) { this.rol = rol; }
}
