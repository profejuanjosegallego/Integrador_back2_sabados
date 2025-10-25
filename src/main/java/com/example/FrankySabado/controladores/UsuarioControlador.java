package com.example.FrankySabado.controladores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.LoginRequestDTO;
import com.example.FrankySabado.modelos.dtos.LoginResponseDTO;
import com.example.FrankySabado.servicios.UsuarioServicio;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio servicio;

    // ========== REGISTRO ==========

    @PostMapping
    public ResponseEntity<?> activarPeticionGuardar(@RequestBody Usuario datos) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.guardarUsuarioGenerico(datos));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // ========== LOGIN ==========

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO responseDTO = servicio.login(loginRequest);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.getMessage());
        }
    }

    // ========== CONSULTAS ==========

    @GetMapping
    public ResponseEntity<?> activarPeticionBuscarTodos() {
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

    @GetMapping("/{id}")
    public ResponseEntity<?> activarPeticionBuscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarUsuarioPorId(id));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

@GetMapping("/correo/{correo}")
public ResponseEntity<?> activarPeticionBuscarPorCorreo(@PathVariable String correo) {
    try {
        // Llamar al NUEVO método que devuelve Usuario completo
        Usuario usuario = this.servicio.buscarUsuarioCompletoPorCorreo(correo);
        
        // Construir respuesta con ID incluido
        Map<String, Object> response = new HashMap<>();
        response.put("id", usuario.getId());
        response.put("nombre", usuario.getNombre());
        response.put("correo", usuario.getCorreo());
        response.put("rol", usuario.getRol().toString());
        response.put("estado", usuario.getEstado().toString());
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    } catch (Exception error) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error.getMessage());
    }
}



    // ========== ACTUALIZACIÓN Y ELIMINACIÓN BÁSICA (DEPRECADAS - Usar endpoints específicos de admin o perfil) ==========

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario datos) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.actualizarUsuario(id, datos));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.eliminarUsuario(id));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    // ========== ADMIN: EDITAR CUALQUIER USUARIO ==========

    @PutMapping("/admin/{idAdmin}/usuarios/{id}")
    public ResponseEntity<?> actualizarUsuarioComoAdmin(
            @PathVariable Integer idAdmin,
            @PathVariable Integer id,
            @RequestBody Usuario datos) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(servicio.actualizarUsuarioComoAdmin(idAdmin, id, datos));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(error.getMessage());
        }
    }

    // ========== ADMIN: ELIMINAR CUALQUIER USUARIO ==========

    @DeleteMapping("/admin/{idAdmin}/usuarios/{id}")
    public ResponseEntity<?> eliminarUsuarioComoAdmin(
            @PathVariable Integer idAdmin,
            @PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(servicio.eliminarUsuarioComoAdmin(idAdmin, id));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(error.getMessage());
        }
    }

    // ========== USUARIO: EDITAR SU PROPIO PERFIL ==========

    @PutMapping("/{id}/perfil")
    public ResponseEntity<?> actualizarPropioUsuario(
            @PathVariable Integer id,
            @RequestBody Usuario datos) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(servicio.actualizarPropioUsuario(id, datos));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // ========== DASHBOARD (CONTROLADOR ANIDADO) ==========

    @RestController
    @RequestMapping("/dashboard")
    public class DashboardControlador {

        @GetMapping
        public ResponseEntity<?> obtenerDatosDashboard() {
            Map<String, Object> datos = new HashMap<>();
            datos.put("totalEstudiantes", 245);
            datos.put("asistenciaMensual", "92%");
            datos.put("rendimiento", "78%");
            datos.put("pendientes", 12);
            return ResponseEntity.ok(datos);
        }
    }
}
