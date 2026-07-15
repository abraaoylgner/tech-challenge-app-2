package com.pos.tech.challenge2.app.adapter.out.repository;

import com.pos.tech.challenge2.app.adapter.out.repository.mapper.ItemCardapioEntityMapper;
import com.pos.tech.challenge2.app.core.domain.ItemCardapio;
import com.pos.tech.challenge2.app.core.port.out.ItemCardapioOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ItemCardapioRepositoryAdapter implements ItemCardapioOutputPort {

    private final ItemCardapioRepository repository;
    private final ItemCardapioEntityMapper mapper;

    @Override
    public ItemCardapio salvar(ItemCardapio item) {
        var entity = mapper.toEntity(item);
        var entitySalva = repository.save(entity);
        return mapper.toDomain(entitySalva);
    }

    @Override
    public Optional<ItemCardapio> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ItemCardapio> buscarTodosPorRestaurante(Long idRestaurante) {
        return repository.findByRestauranteId(idRestaurante).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}