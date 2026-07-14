package com.pos.tech.challenge2.app.adapter.in.controller.mapper;

import com.pos.tech.challenge2.app.adapter.in.controller.request.RestauranteRequestDTO;
import com.pos.tech.challenge2.app.adapter.in.controller.response.RestauranteResponseDTO;
import com.pos.tech.challenge2.app.core.domain.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    @Mapping(target = "id", ignore = true)
    Restaurante toDomain(RestauranteRequestDTO request);

    RestauranteResponseDTO toDto(Restaurante domain);
}