package com.pos.tech.challenge2.app.core.usecase;

import com.pos.tech.challenge2.app.core.domain.ItemCardapio;
import com.pos.tech.challenge2.app.core.port.in.ItemCardapioInputPort;
import com.pos.tech.challenge2.app.core.port.out.ItemCardapioOutputPort;
import com.pos.tech.challenge2.app.core.port.out.RestauranteOutputPort;

import java.util.List;

public class ItemCardapioUseCase implements ItemCardapioInputPort {

    private final ItemCardapioOutputPort itemCardapioOutputPort;
    private final RestauranteOutputPort restauranteOutputPort;

    public ItemCardapioUseCase(ItemCardapioOutputPort itemCardapioOutputPort, RestauranteOutputPort restauranteOutputPort) {
        this.itemCardapioOutputPort = itemCardapioOutputPort;
        this.restauranteOutputPort = restauranteOutputPort;
    }

    @Override
    public ItemCardapio criar(ItemCardapio item) {
        validarRestaurante(item.getIdRestaurante());
        return itemCardapioOutputPort.salvar(item);
    }

    @Override
    public ItemCardapio buscarPorId(Long id) {
        return itemCardapioOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado."));
    }

    @Override
    public List<ItemCardapio> buscarTodosPorRestaurante(Long idRestaurante) {
        validarRestaurante(idRestaurante);
        return itemCardapioOutputPort.buscarTodosPorRestaurante(idRestaurante);
    }

    @Override
    public ItemCardapio atualizar(Long id, ItemCardapio itemAtualizado) {
        var itemExistente = buscarPorId(id);

        if (!itemExistente.getIdRestaurante().equals(itemAtualizado.getIdRestaurante())) {
            validarRestaurante(itemAtualizado.getIdRestaurante());
        }

        itemExistente.setNome(itemAtualizado.getNome());
        itemExistente.setDescricao(itemAtualizado.getDescricao());
        itemExistente.setPreco(itemAtualizado.getPreco());
        itemExistente.setDisponivelApenasRestaurante(itemAtualizado.isDisponivelApenasRestaurante());
        itemExistente.setCaminhoFoto(itemAtualizado.getCaminhoFoto());
        itemExistente.setIdRestaurante(itemAtualizado.getIdRestaurante());

        return itemCardapioOutputPort.salvar(itemExistente);
    }

    @Override
    public void excluir(Long id) {
        buscarPorId(id);
        itemCardapioOutputPort.excluir(id);
    }

    private void validarRestaurante(Long idRestaurante) {
        restauranteOutputPort.buscarPorId(idRestaurante)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado com o ID: " + idRestaurante));
    }
}