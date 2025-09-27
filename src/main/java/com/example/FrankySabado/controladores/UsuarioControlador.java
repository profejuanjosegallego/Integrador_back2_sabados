package com.example.FrankySabado.controladores;

import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")//Aca bautizo el servicio (Api)
public class UsuarioControlador {

    //Llamar al servicio
    //inyectar la dependencia al servicio
    @Autowired
     UsuarioServicio servicio;

    //En el controlador Activo la recepcion y el envio de respuestas hacia el cliente por cada funcion que tenga en mi servicio

    //1. Activando el api para guardar un usuario
    @PostMapping
    public ResponseEntity<?>activarPeticionGuardar(@RequestBody Usuario datos){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.guardarUsuarioGenerico(datos));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
    //2.Ativando el servicio para buscar el servicio por todos
    @GetMapping
    public ResponseEntity<?>activarPeticionBuscarTodos(){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarTodosLosUsuarios());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
    //Ativando el servicio para buscar el servicio por id
    @GetMapping("/{id}")
    public ResponseEntity<?>activarPeticionBuscarPorId(@PathVariable Integer id){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarUsuarioPorId(id));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
