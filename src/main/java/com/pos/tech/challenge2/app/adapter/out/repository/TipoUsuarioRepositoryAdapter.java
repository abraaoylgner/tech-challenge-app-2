package com.pos.tech.challenge2.app.adapter.out.repository;

import com.pos.tech.challenge2.app.adapter.out.repository.mapper.TipoUsuarioEntityMapper;
import com.pos.tech.challenge2.app.core.domain.TipoUsuario;
import com.pos.tech.challenge2.app.core.port.out.TipoUsuarioOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TipoUsuarioRepositoryAdapter implements TipoUsuarioOutputPort {
    private final TipoUsuarioRepository repository;
    private final TipoUsuarioEntityMapper mapper;

    @Override
    public TipoUsuario salvar(TipoUsuario tipoUsuario) {
        return mapper.toDomain(repository.save(mapper.toEntity(tipoUsuario)));
    }

    @Override
    public Optional<TipoUsuario> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TipoUsuario> buscarTodos() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}