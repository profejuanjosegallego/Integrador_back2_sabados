package com.example.FrankySabado.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table()
public class TablaJuanEstebanGaviria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (nullable = false,length = 50)

    private  String nombre;
    @Column (nullable = false,length = 50)
    private String direccion;

    @OneToOne
    @JoinColumn(name = "fk_tablaEstebanZapata", referencedColumnName = "id")
    @JsonManagedReference (value = "relaciontblestebantbljuan")
    private TablaEstebanZapata tablaEstebanZapata;

    @OneToOne(mappedBy = "tablaJuanJoseSanchez")
    @JsonBackReference(value = "relaciontablajuanjosetablajuanesteban")
    private TablaJuanJoseSanchez tablaJuanJoseSanchez;
    public TablaJuanEstebanGaviria() {
    }


    public TablaJuanEstebanGaviria(Integer id, String nombre, String direccion) {
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
