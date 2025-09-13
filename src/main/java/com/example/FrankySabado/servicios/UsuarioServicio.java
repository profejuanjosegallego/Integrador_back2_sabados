package com.example.FrankySabado.servicios;
import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.UsuarioGenericoDTO;
import com.example.FrankySabado.modelos.mapas.IMapaUsuario;
import com.example.FrankySabado.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {
    /// Lo primero que hace el servicio es llamar al repositorio
    /// Se conoce como inyectar la dependencia del repositorio
    @Autowired
    private IUsuarioRepositorio repositorioUsuario;
    @Autowired
    private IMapaUsuario mapaUsuario;


    /// Programar cada una de las funciones para activar las consultas del API
    //1. Servicio para guardar 1 usuario
    public UsuarioGenericoDTO guardarUsuarioGenerico(Usuario datosUsuario) throws Exception {
        try {
            //Quiero intentar guardar el Usuario
            return this.mapaUsuario.convertir_a_dto(this.repositorioUsuario.save(datosUsuario));

        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcionDeError() + error.getMessage());
        }
    }

    //2. Servicio para buscar un Usuario con ID
    public UsuarioGenericoDTO buscarUsuarioPorID(Integer idUsuarioABuscar) throws Exception {
        try {
            Optional<Usuario> usuarioEncontrado = this.repositorioUsuario.findById(idUsuarioABuscar);
            if (usuarioEncontrado.isPresent()) { /// Lo Encontreee
                return this.mapaUsuario.convertir_a_dto(usuarioEncontrado.get());
            } else { /// NO LO ENCONTRE
                throw new Exception(MensajeError.USUARIO_NO_ENCONTRADO.getDescripcionDeError());
            }

            //return this.mapaUsuario.convertir_a_dto(this.repositorioUsuario.findById(idUsuarioABuscar));
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcionDeError() + error.getMessage());
        }
    }

    //3. BUSCAR POR CORREO
    public UsuarioGenericoDTO buscarUsuarioPorCorreo(String correo) throws Exception {
        try {
            Optional<Usuario> usuarioEncontrado = this.repositorioUsuario.findByCorreo(correo);
            if (usuarioEncontrado.isPresent()) { /// Lo Encontreee
                return this.mapaUsuario.convertir_a_dto(usuarioEncontrado.get());
            } else { /// NO LO ENCONTRE
                throw new Exception(MensajeError.USUARIO_NO_ENCONTRADO.getDescripcionDeError());
            }

            //return this.mapaUsuario.convertir_a_dto(this.repositorioUsuario.findById(idUsuarioABuscar));
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcionDeError() + error.getMessage());
        }
    }

    /// 4. Servicio pára buscar todos los registreos de la tabla
    public List<UsuarioGenericoDTO> buscarTodosLosUsuarios() throws Exception {
        try {
            return this.mapaUsuario.convertir_lista_a_dto(this.repositorioUsuario.findAll());
        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcionDeError() + error.getMessage());
        }
    }

    //5. Servicio para buscar todos los usuarios que tengan un nombre especifico
    public List<UsuarioGenericoDTO> buscarUsuariosPorNombres(String nombre) throws Exception {
        try {
           return this.mapaUsuario.convertir_lista_a_dto(this.repositorioUsuario.findByNombre(nombre));

        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcionDeError() + error.getMessage());
        }
    }

}







