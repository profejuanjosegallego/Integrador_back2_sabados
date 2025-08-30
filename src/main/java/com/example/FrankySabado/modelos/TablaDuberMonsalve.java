package com.example.FrankySabado.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tabladubermonsalve")
public class TablaDuberMonsalve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String direccion;

    @OneToOne
    @JoinColumn(name = "fk_juanjose", referencedColumnName = "id")
    @JsonManagedReference(value = "juanjoseduberandres")
    private TablaJuanJoseVilla tablaJuanJoseVilla;

    public TablaDuberMonsalve() {
    }

    public TablaDuberMonsalve(Integer id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
