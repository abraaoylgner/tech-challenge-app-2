package com.pos.tech.challenge2.app.core.port.out;
import com.pos.tech.challenge2.app.core.domain.Restaurante;
import java.util.List;
import java.util.Optional;

public interface RestauranteOutputPort {
    Restaurante salvar(Restaurante restaurante);
    Optional<Restaurante> buscarPorId(Long id);
    List<Restaurante> buscarTodos();
    void excluir(Long id);
}