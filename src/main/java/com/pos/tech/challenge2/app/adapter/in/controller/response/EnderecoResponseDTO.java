package com.pos.tech.challenge2.app.adapter.in.controller.response;

public record EnderecoResponseDTO(
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep
) {}