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
}
