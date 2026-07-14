package com.pos.tech.challenge2.app.core.domain;

import java.math.BigDecimal;

public class ItemCardapio {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean disponivelApenasRestaurante;
    private String caminhoFoto;
    private Long idRestaurante;

    public ItemCardapio(Long id, String nome, String descricao, BigDecimal preco, boolean disponivelApenasRestaurante, String caminhoFoto, Long idRestaurante) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivelApenasRestaurante = disponivelApenasRestaurante;
        this.caminhoFoto = caminhoFoto;
        this.idRestaurante = idRestaurante;
    }
}