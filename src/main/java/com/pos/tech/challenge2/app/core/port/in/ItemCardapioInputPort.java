package com.pos.tech.challenge2.app.core.port.in;

import com.pos.tech.challenge2.app.core.domain.ItemCardapio;
import java.util.List;

public interface ItemCardapioInputPort {
    ItemCardapio criar(ItemCardapio item);
    ItemCardapio buscarPorId(Long id);
    List<ItemCardapio> buscarTodosPorRestaurante(Long idRestaurante);
    ItemCardapio atualizar(Long id, ItemCardapio item);
    void excluir(Long id);
}