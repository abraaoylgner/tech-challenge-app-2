package com.pos.tech.challenge2.app.adapter.in.controller.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank String login,
        @NotBlank String senha
) {}