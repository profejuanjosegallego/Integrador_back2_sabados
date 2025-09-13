package com.example.FrankySabado.repositorios;

import com.example.FrankySabado.modelos.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEstudianteRepositorio extends JpaRepository<Estudiante, Integer> {
    //JPA HABILITA CONSULTAS PERSONALIZADAS

    //Personalizando mis consultas en sql a travez de jpa
    List<Estudiante> findByPromedio(Double promedio);
}

