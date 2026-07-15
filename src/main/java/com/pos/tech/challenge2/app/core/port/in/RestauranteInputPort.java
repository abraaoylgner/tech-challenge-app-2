package com.pos.tech.challenge2.app.core.port.in;
import com.pos.tech.challenge2.app.core.domain.Restaurante;
import java.util.List;

public interface RestauranteInputPort {
    Restaurante criar(Restaurante restaurante);
    Restaurante buscarPorId(Long id);
    List<Restaurante> buscarTodos();
    Restaurante atualizar(Long id, Restaurante restaurante);
    void excluir(Long id);
}