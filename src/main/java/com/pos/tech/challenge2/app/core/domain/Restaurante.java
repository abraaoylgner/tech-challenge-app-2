package com.pos.tech.challenge2.app.core.domain;

public class Restaurante {
    private Long id;
    private String nome;
    private Endereco endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Long idDono;

    public Restaurante(Long id, String nome, Endereco endereco, String tipoCozinha, String horarioFuncionamento, Long idDono) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.idDono = idDono;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
    public String getTipoCozinha() { return tipoCozinha; }
    public void setTipoCozinha(String tipoCozinha) { this.tipoCozinha = tipoCozinha; }
    public String getHorarioFuncionamento() { return horarioFuncionamento; }
    public void setHorarioFuncionamento(String horarioFuncionamento) { this.horarioFuncionamento = horarioFuncionamento; }
    public Long getIdDono() { return idDono; }
    public void setIdDono(Long idDono) { this.idDono = idDono; }
}