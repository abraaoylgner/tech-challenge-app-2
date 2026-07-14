package com.pos.tech.challenge2.app.adapter.in.controller;

import com.pos.tech.challenge2.app.adapter.in.controller.mapper.RestauranteMapper;
import com.pos.tech.challenge2.app.adapter.in.controller.request.RestauranteRequestDTO;
import com.pos.tech.challenge2.app.adapter.in.controller.response.RestauranteResponseDTO;
import com.pos.tech.challenge2.app.core.port.in.RestauranteInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/restaurantes")
@RequiredArgsConstructor
@Tag(name = "Restaurantes", description = "Gerenciamento de estabelecimentos")
public class RestauranteController {

    private final RestauranteInputPort restauranteInputPort;
    private final RestauranteMapper mapper;

    @PostMapping
    @Operation(summary = "Criar um restaurante", description = "Cadastra um novo restaurante vinculando-o a um Dono.")
    public ResponseEntity<RestauranteResponseDTO> criar(@RequestBody @Valid RestauranteRequestDTO request) {
        var domain = mapper.toDomain(request);
        var restauranteCriado = restauranteInputPort.criar(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(restauranteCriado));
    }

    @GetMapping
    @Operation(summary = "Listar restaurantes", description = "Lista os restaurantes cadastrados.")
    public ResponseEntity<List<RestauranteResponseDTO>> listar() {
        var restaurantes = restauranteInputPort.buscarTodos();
        var response = restaurantes.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(response);
    }
}