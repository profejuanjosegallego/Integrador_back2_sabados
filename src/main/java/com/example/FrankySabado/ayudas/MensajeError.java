package com.example.FrankySabado.ayudas;

public enum MensajeError {
    USUARIO_NO_ENCONTRADO("El usuario no se encuentra en la Base De Datos"),
    ERROR_GENERAL_API("Ups fallamos el API tuvo un problema")
    ;
    private final String descripcionDeError;

    MensajeError(String descripcionDeError) {
        this.descripcionDeError = descripcionDeError;
    }

    public String getDescripcionDeError() {return descripcionDeError;}


}
