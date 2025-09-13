package com.example.FrankySabado.servicios;
import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.modelos.Estudiante;
import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.EstudianteDTO;
import com.example.FrankySabado.modelos.dtos.UsuarioGenericoDTO;
import com.example.FrankySabado.modelos.mapas.IMapaEstudiante;
import com.example.FrankySabado.repositorios.IEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicio {
    @Autowired
    private IEstudianteRepositorio repositorioEstudiante;
    @Autowired
    private IMapaEstudiante mapaEstudiante;

    //1. Servicio para guardar 1 usuario

    public EstudianteDTO guardarEstudiante(Estudiante datoEstudiante) throws Exception{
        try {
            //Quiero intentar guardar el Usuario
            return this.mapaEstudiante.covertirModeloADto(this.repositorioEstudiante.save(datoEstudiante));

        }catch(Exception error){
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcionDeError() + error.getMessage());
        }
    }
    //2. Servicio para buscar un Usuario con ID
    public EstudianteDTO buscarEstudianteioPorID(Integer idEstudianteABuscar) throws Exception{
        try {
            Optional<Estudiante> estudianteEncontrado = this.repositorioEstudiante.findById(idEstudianteABuscar);
            if (estudianteEncontrado.isPresent()){ /// Lo Encontreee
                return this.mapaEstudiante.covertirModeloADto(estudianteEncontrado.get());
            }else { /// NO LO ENCONTRE
                throw new Exception(MensajeError.USUARIO_NO_ENCONTRADO.getDescripcionDeError());
            }

        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcionDeError() + error.getMessage());
        }
    }

    ///3. Servicio pára buscar todos los registreos de la tabla
    public List<EstudianteDTO> buscarTodosLosEstudiantes()throws Exception{
        try {
            return this.mapaEstudiante.covertirListaADto(this.repositorioEstudiante.findAll());
        }catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcionDeError() + error.getMessage());
        }
    }

    //4. Servicio para buscar el promedio
    public List<EstudianteDTO> buscarEstudiantesPorNombres (Double promedio) throws Exception {
        try {
            return this.mapaEstudiante.covertirListaADto(this.repositorioEstudiante.findByPromedio(promedio));

        } catch (Exception error) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcionDeError() + error.getMessage());
        }


    }
}

