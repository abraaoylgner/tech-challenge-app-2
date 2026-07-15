package com.pos.tech.challenge2.app.adapter.in.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemCardapioRequestDTO(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull @Positive BigDecimal preco,
        @NotNull Boolean disponivelApenasRestaurante,
        String caminhoFoto,
        @NotNull Long idRestaurante
) {}