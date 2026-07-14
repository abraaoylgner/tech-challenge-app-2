package com.pos.tech.challenge2.app.adapter.out.repository;

import com.pos.tech.challenge2.app.adapter.out.repository.mapper.RestauranteEntityMapper;
import com.pos.tech.challenge2.app.core.domain.Restaurante;
import com.pos.tech.challenge2.app.core.port.out.RestauranteOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RestauranteRepositoryAdapter implements RestauranteOutputPort {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteEntityMapper mapper;

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        var entity = mapper.toEntity(restaurante);
        var entitySalva = restauranteRepository.save(entity);
        return mapper.toDomain(entitySalva);
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        return restauranteRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Restaurante> buscarTodos() {
        return restauranteRepository.findAll().stream().map(mapper::toDomain).toList();
    }
}