package com.pos.tech.challenge2.app.adapter.out.repository.mapper;

import com.pos.tech.challenge2.app.adapter.out.repository.entity.RestauranteEntity;
import com.pos.tech.challenge2.app.adapter.out.repository.entity.UsuarioEntity;
import com.pos.tech.challenge2.app.core.domain.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RestauranteEntityMapper {

    @Mapping(source = "dono.id", target = "idDono")
    Restaurante toDomain(RestauranteEntity entity);

    @Mapping(source = "idDono", target = "dono", qualifiedByName = "mapUsuarioEntity")
    RestauranteEntity toEntity(Restaurante domain);

    @Named("mapUsuarioEntity")
    default UsuarioEntity mapUsuarioEntity(Long idDono) {
        if (idDono == null) {
            return null;
        }
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(idDono);
        return usuario;
    }
}