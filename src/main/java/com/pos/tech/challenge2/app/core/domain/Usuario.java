package com.pos.tech.challenge2.app.core.domain;

public class Usuario {
    private Long id;
    private TipoUsuario tipoUsuario;

    public Usuario(Long id, TipoUsuario tipoUsuario) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}