package com.pos.tech.challenge2.app.adapter.in.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestauranteRequestDTO(
        @NotBlank String nome,
        @NotNull @Valid EnderecoRequestDTO endereco,
        @NotBlank String tipoCozinha,
        @NotBlank String horarioFuncionamento,
        @NotNull Long idDono
) {}