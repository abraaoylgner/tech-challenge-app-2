package com.pos.tech.challenge2.app.adapter.in.controller.mapper;

import com.pos.tech.challenge2.app.adapter.in.controller.request.TipoUsuarioRequestDTO;
import com.pos.tech.challenge2.app.adapter.in.controller.response.TipoUsuarioResponseDTO;
import com.pos.tech.challenge2.app.core.domain.TipoUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TipoUsuarioMapper {
    @Mapping(target = "id", ignore = true)
    TipoUsuario toDomain(TipoUsuarioRequestDTO request);
    TipoUsuarioResponseDTO toDto(TipoUsuario domain);
}