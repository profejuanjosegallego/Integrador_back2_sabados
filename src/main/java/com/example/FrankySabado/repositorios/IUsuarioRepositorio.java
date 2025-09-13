package com.example.FrankySabado.repositorios;

import com.example.FrankySabado.modelos.Usuario;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {    //PONER JPA DESPUES DEL EXTEND, PARA QUE JPA LOP HAGA, dentro de <> se pone El nobre de la clase y despues de una , se pone el tipo de dato
//JPA HABILITA CONSULTAS PERSONALIZADAS

    //Personalizando mis consultas en sql a travez de jpa
    public List<Usuario>findByNombre(String nombre);
    Optional<Usuario>findByCorreo(String correo);
}
