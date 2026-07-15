package com.pos.tech.challenge2.app.adapter.in.controller.response;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        TipoUsuarioResponseDTO tipoUsuario,
        EnderecoResponseDTO endereco,
        LocalDateTime dataUltimaAlteracao
) {}