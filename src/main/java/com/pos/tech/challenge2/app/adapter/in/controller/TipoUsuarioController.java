package com.pos.tech.challenge2.app.adapter.in.controller;

import com.pos.tech.challenge2.app.adapter.in.controller.mapper.TipoUsuarioMapper;
import com.pos.tech.challenge2.app.adapter.in.controller.request.TipoUsuarioRequestDTO;
import com.pos.tech.challenge2.app.adapter.in.controller.response.TipoUsuarioResponseDTO;
import com.pos.tech.challenge2.app.core.port.in.TipoUsuarioInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tipos-usuario")
@RequiredArgsConstructor
@Tag(name = "Tipos de Usuário", description = "CRUD de Tipos de Usuário")
public class TipoUsuarioController {

    private final TipoUsuarioInputPort inputPort;
    private final TipoUsuarioMapper mapper;

    @PostMapping
    @Operation(summary = "Criar tipo de usuário")
    public ResponseEntity<TipoUsuarioResponseDTO> criar(@RequestBody @Valid TipoUsuarioRequestDTO request) {
        var criado = inputPort.criar(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(criado));
    }

    @GetMapping
    @Operation(summary = "Listar tipos de usuário")
    public ResponseEntity<List<TipoUsuarioResponseDTO>> listar() {
        var lista = inputPort.buscarTodos().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(lista);
    }

}