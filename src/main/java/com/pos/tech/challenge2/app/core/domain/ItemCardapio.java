package com.pos.tech.challenge2.app.core.domain;

import java.math.BigDecimal;

public class ItemCardapio {
    private Long id;
    private String nome; // [cite: 37]
    private String descricao; // [cite: 38]
    private BigDecimal preco; // [cite: 39]
    private boolean disponivelApenasRestaurante; // [cite: 40]
    private String caminhoFoto; // [cite: 41]
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