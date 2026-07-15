package com.pos.tech.challenge2.app.adapter.in.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotBlank String nome,
        @Email String email,
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull Long idTipoUsuario,
        @Valid EnderecoRequestDTO endereco
) {}