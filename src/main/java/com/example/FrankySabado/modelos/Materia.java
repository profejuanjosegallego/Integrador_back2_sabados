package com.example.FrankySabado.modelos;

import jakarta.persistence.*;

    @Entity
    @Table(name = "materias")
    public class Materia {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false, length = 100)
        private String nombre;


        @Column(nullable = false, unique = true, length = 50)
        private String codigo;

        @Column(nullable = false, unique = true, length = 20)
        private Integer docente_id;


        public Materia() {
        }

        public Materia(Integer id, String nombre, String codigo, Integer docente_id) {
            this.id = id;
            this.nombre = nombre;
            this.codigo = codigo;
            this.docente_id = docente_id;
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

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public Integer getDocente_id() {
            return docente_id;
        }

        public void setDocente_id(Integer docente_id) {
            this.docente_id = docente_id;
        }
    }
