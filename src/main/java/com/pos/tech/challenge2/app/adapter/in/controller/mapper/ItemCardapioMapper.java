package com.pos.tech.challenge2.app.adapter.in.controller.mapper;

import com.pos.tech.challenge2.app.adapter.in.controller.request.ItemCardapioRequestDTO;
import com.pos.tech.challenge2.app.adapter.in.controller.response.ItemCardapioResponseDTO;
import com.pos.tech.challenge2.app.core.domain.ItemCardapio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemCardapioMapper {

    @Mapping(target = "id", ignore = true)
    ItemCardapio toDomain(ItemCardapioRequestDTO request);

    ItemCardapioResponseDTO toDto(ItemCardapio domain);
}