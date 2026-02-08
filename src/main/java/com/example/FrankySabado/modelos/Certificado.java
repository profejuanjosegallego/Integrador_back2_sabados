package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.Institucion;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "certificado")
public class Certificado {

    @Column(name = "nombre",length = 70,nullable = false,unique = false)
    private String nombre;

    @Column(name = "institucion",nullable = false,unique = true)
    @Enumerated(EnumType.STRING)
    private Institucion institucion;

    @Column(name = "fecha",nullable = false)
    @Timestamp
    private LocalDateTime fecha;

    @Column(name = "url_archivo",nullable = false,unique = true)
    private String url_archivo;

    //relacionar con PerfilEstudiante 0neToOne...
    //Pendiente por merge PerfilEstudiante ... para poder relacionar

    public Certificado() {
    }

    public Certificado(String nombre, Institucion institucion, LocalDateTime fecha, String url_archivo) {
        this.nombre = nombre;
        this.institucion = institucion;
        this.fecha = fecha;
        this.url_archivo = url_archivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getUrl_archivo() {
        return url_archivo;
    }

    public void setUrl_archivo(String url_archivo) {
        this.url_archivo = url_archivo;
    }
}
