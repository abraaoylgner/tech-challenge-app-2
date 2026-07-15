package com.pos.tech.challenge2.app.core.port.out;

import com.pos.tech.challenge2.app.core.domain.ItemCardapio;
import java.util.List;
import java.util.Optional;

public interface ItemCardapioOutputPort {
    ItemCardapio salvar(ItemCardapio item);
    Optional<ItemCardapio> buscarPorId(Long id);
    List<ItemCardapio> buscarTodosPorRestaurante(Long idRestaurante);
    void excluir(Long id);
}