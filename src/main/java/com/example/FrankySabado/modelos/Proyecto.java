package com.example.FrankySabado.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;

@Entity
@Table(name = "proyecto")
public class Proyecto {

    @Column(name = "titulo",nullable = false,unique = false)
    private String titulo;

    @Column(name = "descricion",nullable = false,unique = true)
    private String descripcion;

    @Column(name = "url",nullable = false,unique = true)
    private String url;

    @Column(name = "tecnologias",nullable = true,unique = false)
    private String tecnologias;

    //relacionar con PerfilEstudiante 0neToOne...
    //Pendiente por merge PerfilEstudiante ... para poder relacionar

    public Proyecto() {
    }

    public Proyecto(String titulo, String descripcion, String url, String tecnologias) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.tecnologias = tecnologias;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(String tecnologias) {
        this.tecnologias = tecnologias;
    }
}
