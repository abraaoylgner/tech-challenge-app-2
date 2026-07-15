package com.pos.tech.challenge2.app.adapter.in.controller.request;
import jakarta.validation.constraints.NotBlank;
public record TipoUsuarioRequestDTO(@NotBlank String nome) {}