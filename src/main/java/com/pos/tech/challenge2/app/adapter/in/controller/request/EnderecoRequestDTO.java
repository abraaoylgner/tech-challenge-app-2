package com.pos.tech.challenge2.app.adapter.in.controller.request;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDTO(
        @NotBlank String rua,
        @NotBlank String numero,
        @NotBlank String bairro,
        @NotBlank String cidade,
        @NotBlank String estado,
        @NotBlank String cep
) {}