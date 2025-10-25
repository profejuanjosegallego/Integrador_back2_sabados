package com.example.FrankySabado.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FrankySabado.modelos.Docente;
import com.example.FrankySabado.modelos.dtos.DocenteDTO;
import com.example.FrankySabado.modelos.mapas.IMapaDocente;
import com.example.FrankySabado.repositorios.IDocenteRepositorio;

@Service
public class DocenteServicio {

    @Autowired
    private IDocenteRepositorio repositorio;
    @Autowired
    private IMapaDocente mapa;

    // Guardar docente
    public DocenteDTO guardarDocente(Docente datosDocente) throws Exception {
        try {
            return this.mapa.convertirModeloADto(this.repositorio.save(datosDocente));
        } catch (Exception error) {
            throw new Exception("Error al guardar docente: " + error.getMessage());
        }
    }

    // Buscar por ID
    public DocenteDTO buscarDocentePorId(Integer idDocente) throws Exception {
        Optional<Docente> docenteOpt = repositorio.findById(idDocente);
        if (docenteOpt.isPresent()) {
            return mapa.convertirModeloADto(docenteOpt.get());
        } else {
            throw new Exception("Docente no encontrado");
        }
    }

    // Buscar todos
    public List<DocenteDTO> buscarTodosLosDocentes() throws Exception {
        try {
            return mapa.convertirListaADto(repositorio.findAll());
        } catch (Exception error) {
            throw new Exception("Error consultando docentes: " + error.getMessage());
        }
    }

    // Buscar por nombre
    public List<DocenteDTO> buscarDocentesPorNombre(String nombre) throws Exception {
        try {
            return mapa.convertirListaADto(repositorio.findByNombre(nombre));
        } catch (Exception error) {
            throw new Exception("Error buscando docentes por nombre: " + error.getMessage());
        }
    }

    // Buscar por especialidad
    public List<DocenteDTO> buscarDocentesPorEspecialidad(String especialidad) throws Exception {
        try {
            return mapa.convertirListaADto(repositorio.findByEspecialidad(especialidad));
        } catch (Exception error) {
            throw new Exception("Error buscando docentes por especialidad: " + error.getMessage());
        }
    }
}
