package com.example.FrankySabado.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FrankySabado.modelos.Docente;
import com.example.FrankySabado.servicios.DocenteServicio;

@RestController
@RequestMapping("/docentes")
public class DocenteControlador {

    @Autowired
    private DocenteServicio servicio;

    @PostMapping
    public ResponseEntity<?> guardarDocente(@RequestBody Docente datosDocente) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.guardarDocente(datosDocente));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> consultarTodos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarTodosLosDocentes());
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarDocentePorId(id));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> consultarPorNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarDocentesPorNombre(nombre));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<?> consultarPorEspecialidad(@PathVariable String especialidad) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarDocentesPorEspecialidad(especialidad));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }
}
