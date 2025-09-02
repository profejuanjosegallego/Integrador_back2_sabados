package com.example.FrankySabado.modelos;


import com.example.FrankySabado.ayudas.Intereses;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Perfil_Estudiante")
public class PerfilEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resumen",nullable = false,unique = false)
    private String resumen;

    @Column(name = "intereses",nullable = false)
    @Enumerated(EnumType.STRING)
    private Intereses intereses;

    @Column(name = "experiencia",nullable = false,unique = false)
    private String experiencia;

    @Column(name = "proyectos",nullable = false,unique = false)
    private String proyectos;

    @Column(name = "estudiante_id",nullable = false,unique = true)
    private Integer estudiante_id;

    @OneToOne
    @JoinColumn(name = "FK_estudiante",referencedColumnName = "id")
    @JsonManagedReference(value = "relacionperfilestudiante_estudiante")
    private Estudiante estudiante;

    public PerfilEstudiante() {
    }

    public PerfilEstudiante(Integer id, String resumen, Intereses intereses, String experiencia, String proyectos, Integer estudiante_id) {
        this.id = id;
        this.resumen = resumen;
        this.intereses = intereses;
        this.experiencia = experiencia;
        this.proyectos = proyectos;
        this.estudiante_id = estudiante_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Intereses getIntereses() {
        return intereses;
    }

    public void setIntereses(Intereses intereses) {
        this.intereses = intereses;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getProyectos() {
        return proyectos;
    }

    public void setProyectos(String proyectos) {
        this.proyectos = proyectos;
    }

    public Integer getEstudiante_id() {
        return estudiante_id;
    }

    public void setEstudiante_id(Integer estudiante_id) {
        this.estudiante_id = estudiante_id;
    }
}
