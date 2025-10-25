package com.example.FrankySabado.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FrankySabado.modelos.Docente;

@Repository
public interface IDocenteRepositorio extends JpaRepository<Docente, Integer> {
    List<Docente> findByEspecialidad(String especialidad);
    List<Docente> findByNombre(String nombre);
}
