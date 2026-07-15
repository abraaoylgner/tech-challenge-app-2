package com.pos.tech.challenge2.app.adapter.in.controller.response;

import java.math.BigDecimal;

public record ItemCardapioResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponivelApenasRestaurante,
        String caminhoFoto,
        Long idRestaurante
) {}