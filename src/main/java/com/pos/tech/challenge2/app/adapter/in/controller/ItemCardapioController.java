package com.pos.tech.challenge2.app.adapter.in.controller;

import com.pos.tech.challenge2.app.adapter.in.controller.mapper.ItemCardapioMapper;
import com.pos.tech.challenge2.app.adapter.in.controller.request.ItemCardapioRequestDTO;
import com.pos.tech.challenge2.app.adapter.in.controller.response.ItemCardapioResponseDTO;
import com.pos.tech.challenge2.app.core.port.in.ItemCardapioInputPort;
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
@RequestMapping("/v1/itens-cardapio")
@RequiredArgsConstructor
@Tag(name = "Cardápio", description = "Gerenciamento de Itens do Cardápio")
public class ItemCardapioController {

    private final ItemCardapioInputPort itemCardapioInputPort;
    private final ItemCardapioMapper mapper;

    @PostMapping
    @Operation(summary = "Criar item do cardápio", description = "Cadastra um novo prato vinculando-o a um Restaurante.")
    @ApiResponse(responseCode = "201", description = "Item criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<ItemCardapioResponseDTO> criar(@RequestBody @Valid ItemCardapioRequestDTO request) {
        var domain = mapper.toDomain(request);
        var itemCriado = itemCardapioInputPort.criar(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(itemCriado));
    }

    @GetMapping("/restaurante/{idRestaurante}")
    @Operation(summary = "Listar itens por restaurante", description = "Lista todo o cardápio de um restaurante específico.")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<List<ItemCardapioResponseDTO>> listarPorRestaurante(@PathVariable Long idRestaurante) {
        var itens = itemCardapioInputPort.buscarTodosPorRestaurante(idRestaurante);
        var response = itens.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar item por ID")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Item do cardápio não encontrado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<ItemCardapioResponseDTO> buscarPorId(@PathVariable Long id) {
        var item = itemCardapioInputPort.buscarPorId(id);
        return ResponseEntity.ok(mapper.toDto(item));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar item do cardápio")
    @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Item do cardápio não encontrado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<ItemCardapioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ItemCardapioRequestDTO request) {
        var domain = mapper.toDomain(request);
        var itemAtualizado = itemCardapioInputPort.atualizar(id, domain);
        return ResponseEntity.ok(mapper.toDto(itemAtualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir item do cardápio")
    @ApiResponse(responseCode = "204", description = "Item excluído com sucesso (Sem conteúdo de retorno)")
    @ApiResponse(responseCode = "403", description = "Acesso negado (Token inválido ou expirado)", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Item do cardápio não encontrado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        itemCardapioInputPort.excluir(id);
        return ResponseEntity.noContent().build();
    }
}