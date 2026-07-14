package com.pos.tech.challenge2.app.adapter.in.controller.response;

public record RestauranteResponseDTO(
        Long id,
        String nome,
        EnderecoResponseDTO endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long idDono
) {}