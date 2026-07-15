package com.pos.tech.challenge2.app.adapter.out.repository.mapper;
import com.pos.tech.challenge2.app.adapter.out.repository.entity.TipoUsuarioEntity;
import com.pos.tech.challenge2.app.core.domain.TipoUsuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoUsuarioEntityMapper {
    TipoUsuario toDomain(TipoUsuarioEntity entity);
    TipoUsuarioEntity toEntity(TipoUsuario domain);
}