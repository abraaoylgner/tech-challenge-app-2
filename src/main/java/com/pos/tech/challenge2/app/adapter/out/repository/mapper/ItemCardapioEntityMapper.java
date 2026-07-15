package com.pos.tech.challenge2.app.adapter.out.repository.mapper;

import com.pos.tech.challenge2.app.adapter.out.repository.entity.ItemCardapioEntity;
import com.pos.tech.challenge2.app.adapter.out.repository.entity.RestauranteEntity;
import com.pos.tech.challenge2.app.core.domain.ItemCardapio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ItemCardapioEntityMapper {

    @Mapping(source = "restaurante.id", target = "idRestaurante")
    ItemCardapio toDomain(ItemCardapioEntity entity);

    @Mapping(source = "idRestaurante", target = "restaurante", qualifiedByName = "mapRestauranteEntity")
    ItemCardapioEntity toEntity(ItemCardapio domain);

    @Named("mapRestauranteEntity")
    default RestauranteEntity mapRestauranteEntity(Long idRestaurante) {
        if (idRestaurante == null) {
            return null;
        }
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setId(idRestaurante);
        return restaurante;
    }
}