package com.example.FrankySabado.modelos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

    @Entity
    @Table(name = "TablasJuanJoseSanchez")
    public class TablaJuanJoseSanchez {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer Id;

        @Column(name = "nombre", length = 100, unique = false, nullable = false)
        private String nombre;

        @Column(name = "direccion", length = 150, unique = false, nullable = false)
        private String direccion;

        @OneToOne(mappedBy = "tablaJuanJoseSanchez")
        @JsonBackReference(value = "relaciontablajuanjosesancheztablaesteban")
        private TablaEstebanZapata tablaEstebanZapata;

        @OneToOne
        @JoinColumn(name = "fk_tablaJuanEsteban", referencedColumnName = "Id")
        @JsonManagedReference(value = "relaciontablajuanjosetablajuanesteban")
        private TablaJuanEstebanGaviria tablaJuanEstebanGaviria;

        public TablaJuanJoseSanchez() {}

        public TablaJuanJoseSanchez(Integer id, String nombre, String direccion) {
            Id = id;
            this.nombre = nombre;
            this.direccion = direccion;
        }

        public Integer getId() {return Id;}
        public void setId(Integer id) {Id = id;}
        public String getNombre() {return nombre;}
        public void setNombre(String nombre) {this.nombre = nombre;}
        public String getDireccion() {return direccion;}
        public void setDireccion(String direccion) {this.direccion = direccion;}


    }


