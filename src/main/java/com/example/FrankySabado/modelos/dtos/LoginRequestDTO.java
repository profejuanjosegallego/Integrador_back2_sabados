package com.example.FrankySabado.modelos.dtos;

public class LoginRequestDTO {
    private String correo;
    private String contraseña;

    public LoginRequestDTO() {}
    public LoginRequestDTO(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

}
