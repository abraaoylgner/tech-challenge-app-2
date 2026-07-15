package com.pos.tech.challenge2.app.adapter.in.controller;

import com.pos.tech.challenge2.app.adapter.in.controller.mapper.RestauranteMapper;
import com.pos.tech.challenge2.app.adapter.in.controller.request.RestauranteRequestDTO;
import com.pos.tech.challenge2.app.adapter.in.controller.response.RestauranteResponseDTO;
import com.pos.tech.challenge2.app.core.port.in.RestauranteInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<RestauranteResponseDTO> criar(@RequestBody @Valid RestauranteRequestDTO request) {
        var domain = mapper.toDomain(request);
        var restauranteCriado = restauranteInputPort.criar(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(restauranteCriado));
    }

    @GetMapping
    @Operation(summary = "Listar restaurantes", description = "Lista os restaurantes cadastrados.")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<List<RestauranteResponseDTO>> listar() {
        var restaurantes = restauranteInputPort.buscarTodos();
        var response = restaurantes.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar restaurante por ID", description = "Retorna os detalhes de um restaurante específico.")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<RestauranteResponseDTO> buscarPorId(@PathVariable Long id) {
        var restaurante = restauranteInputPort.buscarPorId(id);
        return ResponseEntity.ok(mapper.toDto(restaurante));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar restaurante", description = "Atualiza os dados de um restaurante existente.")
    @ApiResponse(responseCode = "200", description = "Restaurante atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<RestauranteResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid RestauranteRequestDTO request) {
        var domain = mapper.toDomain(request);
        var restauranteAtualizado = restauranteInputPort.atualizar(id, domain);
        return ResponseEntity.ok(mapper.toDto(restauranteAtualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir restaurante", description = "Remove um restaurante do sistema.")
    @ApiResponse(responseCode = "204", description = "Restaurante excluído com sucesso (Sem conteúdo de retorno)")
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        restauranteInputPort.excluir(id);
        return ResponseEntity.noContent().build();
    }
}