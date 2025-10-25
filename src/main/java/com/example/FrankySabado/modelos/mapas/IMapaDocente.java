package com.example.FrankySabado.modelos.mapas;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.FrankySabado.modelos.Docente;
import com.example.FrankySabado.modelos.dtos.DocenteDTO;

@Mapper(componentModel = "spring")
public interface IMapaDocente {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "especialidad", target = "especialidad")
    @Mapping(source = "usuario.rol", target = "rol")
    DocenteDTO convertirModeloADto(Docente docente);

    List<DocenteDTO> convertirListaADto(List<Docente> lista);
}
