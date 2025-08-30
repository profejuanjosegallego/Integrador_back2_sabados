package com.example.FrankySabado.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "TablaEstebanZapata")
public class TablaEstebanZapata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (nullable = false, length = 100)
    private String nombre;
    @Column (nullable = false, length = 100)
    private String direccion;

    @OneToOne(mappedBy = "tablaEstebanZapata")
    @JsonBackReference(value = "relaciontablajuanestebangaviriatablaesteban")
    private TablaJuanEstebanGaviria tablaJuanEstebanGaviria;

    @OneToOne
    @JoinColumn(name = "fk_tablaJuanJoseSanchez", referencedColumnName = "id")
    @JsonManagedReference(value = "relaciontablajuanjosetablaesteban")
    private TablaJuanJoseSanchez tablaJuanJoseSanchez;

    public TablaEstebanZapata() {
    }

    public TablaEstebanZapata(Integer id, String nombre, String direccion) {
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
