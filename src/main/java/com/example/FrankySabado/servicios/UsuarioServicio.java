package com.example.FrankySabado.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FrankySabado.ayudas.Estados;
import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.ayudas.Roles;
import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.LoginRequestDTO;
import com.example.FrankySabado.modelos.dtos.LoginResponseDTO;
import com.example.FrankySabado.modelos.dtos.UsuarioGenericoDTO;
import com.example.FrankySabado.modelos.mapas.IMapaUsuario;
import com.example.FrankySabado.repositorios.IUsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio repositorio;

    @Autowired
    private IMapaUsuario mapa;

    // ========== REGISTRO (CREAR USUARIO) ==========

    // Servicio de registro con validaciones 
    public UsuarioGenericoDTO guardarUsuarioGenerico(Usuario datosUsuario) throws Exception {
        // Validación: email único
        Optional<Usuario> usuarioExistente = repositorio.findByCorreo(datosUsuario.getCorreo());
        if (usuarioExistente.isPresent()) {
            throw new Exception("El correo ya está registrado.");
        }
        // Validación: longitud mínima de contraseña
        if (datosUsuario.getContraseña() == null || datosUsuario.getContraseña().length() < 6) {
            throw new Exception("La contraseña debe tener al menos 6 caracteres.");
        }
        // Validación: formato básico de correo
        if (datosUsuario.getCorreo() == null || !datosUsuario.getCorreo().contains("@")) {
            throw new Exception("El correo no tiene un formato válido.");
        }
        // Asignar valores por defecto de rol
        if (datosUsuario.getRol() == null) datosUsuario.setRol(Roles.Estudiante); // o Docente/Admin
        if (datosUsuario.getEstado() == null) datosUsuario.setEstado(Estados.Activo);

        try {
            return this.mapa.convertir_a_dto(this.repositorio.save(datosUsuario));
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + error.getMessage());
        }
    }

    // Servicio para buscar usuario por ID
    public UsuarioGenericoDTO buscarUsuarioPorId(Integer idUsuarioABuscar) throws Exception {
        try {
            Optional<Usuario> usuarioEncontrado = this.repositorio.findById(idUsuarioABuscar);
            if (usuarioEncontrado.isPresent()) {
                return this.mapa.convertir_a_dto(usuarioEncontrado.get());
            } else {
                throw new Exception(MensajeError.USUARIO_NO_ENCONTRADO.getDescripcion());
            }
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + error.getMessage());
        }
    }

    // Servicio para buscar usuario por correo
    public UsuarioGenericoDTO buscarUsuarioPorCorreo(String correoABuscar) throws Exception {
        try {
            Optional<Usuario> usuarioEncontrado = this.repositorio.findByCorreo(correoABuscar);
            if (usuarioEncontrado.isPresent()) {
                return this.mapa.convertir_a_dto(usuarioEncontrado.get());
            } else {
                throw new Exception(MensajeError.USUARIO_NO_ENCONTRADO.getDescripcion());
            }
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + error.getMessage());
        }
    }

    // NUEVO: Servicio para buscar usuario COMPLETO por correo (con ID)
public Usuario buscarUsuarioCompletoPorCorreo(String correoABuscar) throws Exception {
    try {
        Optional<Usuario> usuarioEncontrado = this.repositorio.findByCorreo(correoABuscar);
        if (usuarioEncontrado.isPresent()) {
            return usuarioEncontrado.get(); // Devuelve el Usuario completo con ID
        } else {
            throw new Exception(MensajeError.USUARIO_NO_ENCONTRADO.getDescripcion());
        }
    } catch (Exception error) {
        throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + error.getMessage());
    }
}


    // Servicio para buscar todos los usuarios
    public List<UsuarioGenericoDTO> buscarTodosLosUsuarios() throws Exception {
        try {
            return this.mapa.convertir_lista_a_dto(this.repositorio.findAll());
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + error.getMessage());
        }
    }

    // Servicio para buscar usuarios por nombre
    public List<UsuarioGenericoDTO> buscarUsuariosPorNombre(String nombre) throws Exception {
        try {
            return this.mapa.convertir_lista_a_dto(this.repositorio.findByNombre(nombre));
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + error.getMessage());
        }
    }

        // ========== LOGIN ==========
    // Servicio para login (autenticación básica)
    public LoginResponseDTO login(LoginRequestDTO loginRequest) throws Exception {
        Optional<Usuario> usuarioOpt = this.repositorio.findByCorreo(loginRequest.getCorreo());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getContraseña().equals(loginRequest.getContraseña())) {
                return new LoginResponseDTO(usuario.getNombre(), usuario.getCorreo(), usuario.getRol());
            } else {
                throw new Exception("Contraseña incorrecta");
            }
        } else {
            throw new Exception("Correo no registrado");
        }
    }

    // ========== ACTUALIZACIÓN BÁSICA (MÉTODO INTERNO) ==========

    // Servicio para actualizar usuario
public UsuarioGenericoDTO actualizarUsuario(Integer id, Usuario datosUsuario) throws Exception {
    // Validar que el usuario a actualizar existe
    Optional<Usuario> usuarioExistente = repositorio.findById(id);
    if (!usuarioExistente.isPresent()) {
        throw new Exception("Usuario no encontrado con ID: " + id);
    }

    Usuario usuarioActual = usuarioExistente.get();

    // Validar que el correo no esté siendo usado por otro usuario
    Optional<Usuario> usuarioConCorreo = repositorio.findByCorreo(datosUsuario.getCorreo());
    if (usuarioConCorreo.isPresent() && !usuarioConCorreo.get().getId().equals(id)) {
        throw new Exception("El correo ya está registrado por otro usuario.");
    }

    // Validar longitud de contraseña (solo si se envía una nueva)
    if (datosUsuario.getContraseña() != null && !datosUsuario.getContraseña().isEmpty()) {
        if (datosUsuario.getContraseña().length() < 6) {
            throw new Exception("La contraseña debe tener al menos 6 caracteres.");
        }
        usuarioActual.setContraseña(datosUsuario.getContraseña());
    }

    // Actualizar solo los campos permitidos
    usuarioActual.setNombre(datosUsuario.getNombre());
    usuarioActual.setCorreo(datosUsuario.getCorreo());
    if (datosUsuario.getRol() != null) usuarioActual.setRol(datosUsuario.getRol());
    if (datosUsuario.getEstado() != null) usuarioActual.setEstado(datosUsuario.getEstado());

    try {
        return this.mapa.convertir_a_dto(this.repositorio.save(usuarioActual));
    } catch (Exception error) {
        throw new Exception("Error al actualizar usuario: " + error.getMessage());
    }
}
 
// ========== ELIMINACIÓN BÁSICA (MÉTODO INTERNO) ==========
// Servicio para eliminar usuario
public String eliminarUsuario(Integer id) throws Exception {
    // Verificar que el usuario existe
    Optional<Usuario> usuarioOpt = repositorio.findById(id);
    if (!usuarioOpt.isPresent()) {
        throw new Exception("Usuario no encontrado con ID: " + id);
    }
    
    try {
        repositorio.deleteById(id);
        return "Usuario eliminado correctamente";
    } catch (Exception error) {
        throw new Exception("Error al eliminar usuario: " + error.getMessage());
    }
}

// ========== MÉTODOS AUXILIARES ==========
    
    private boolean esAdministrador(Integer idUsuario) throws Exception {
        Optional<Usuario> usuario = repositorio.findById(idUsuario);
        if (usuario.isPresent()) {
            return usuario.get().getRol() == Roles.Administrador;
        }
        throw new Exception("Usuario no encontrado");
    }

    // ========== ADMIN: EDITAR CUALQUIER USUARIO ==========
    
    public UsuarioGenericoDTO actualizarUsuarioComoAdmin(Integer idAdmin, Integer idUsuarioAActualizar, Usuario datosUsuario) throws Exception {
        if (!esAdministrador(idAdmin)) {
            throw new Exception("No tienes permisos. Se requiere rol de Administrador.");
        }
        return actualizarUsuario(idUsuarioAActualizar, datosUsuario);
    }

    // ========== ADMIN: ELIMINAR CUALQUIER USUARIO ==========
    
    public String eliminarUsuarioComoAdmin(Integer idAdmin, Integer idUsuarioAEliminar) throws Exception {
        if (!esAdministrador(idAdmin)) {
            throw new Exception("No tienes permisos. Se requiere rol de Administrador.");
        }
        return eliminarUsuario(idUsuarioAEliminar);
    }

    // ========== USUARIO: EDITAR SU PROPIO PERFIL ==========
    
    public UsuarioGenericoDTO actualizarPropioUsuario(Integer idUsuario, Usuario datosUsuario) throws Exception {
        Optional<Usuario> usuarioOpt = repositorio.findById(idUsuario);
        if (!usuarioOpt.isPresent()) {
            throw new Exception("Usuario no encontrado");
        }

        Usuario usuarioActual = usuarioOpt.get();

        // Validar que el correo no esté siendo usado por otro usuario
        Optional<Usuario> usuarioConCorreo = repositorio.findByCorreo(datosUsuario.getCorreo());
        if (usuarioConCorreo.isPresent() && !usuarioConCorreo.get().getId().equals(idUsuario)) {
            throw new Exception("El correo ya está registrado por otro usuario.");
        }

        // Actualizar solo campos permitidos (sin rol ni estado)
        usuarioActual.setNombre(datosUsuario.getNombre());
        usuarioActual.setCorreo(datosUsuario.getCorreo());
        
        if (datosUsuario.getContraseña() != null && !datosUsuario.getContraseña().isEmpty()) {
            if (datosUsuario.getContraseña().length() < 6) {
                throw new Exception("La contraseña debe tener al menos 6 caracteres.");
            }
            usuarioActual.setContraseña(datosUsuario.getContraseña());
        }

        try {
            return this.mapa.convertir_a_dto(this.repositorio.save(usuarioActual));
        } catch (Exception error) {
            throw new Exception("Error al actualizar perfil: " + error.getMessage());
        }
    }

}
