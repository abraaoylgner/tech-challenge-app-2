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

    // --- Getters e Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public boolean isDisponivelApenasRestaurante() {
        return disponivelApenasRestaurante;
    }

    public void setDisponivelApenasRestaurante(boolean disponivelApenasRestaurante) {
        this.disponivelApenasRestaurante = disponivelApenasRestaurante;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
}