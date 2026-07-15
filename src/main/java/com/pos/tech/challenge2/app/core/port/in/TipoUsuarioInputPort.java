package com.pos.tech.challenge2.app.core.port.in;
import com.pos.tech.challenge2.app.core.domain.TipoUsuario;
import java.util.List;

public interface TipoUsuarioInputPort {
    TipoUsuario criar(TipoUsuario tipoUsuario);
    TipoUsuario buscarPorId(Long id);
    List<TipoUsuario> buscarTodos();
    TipoUsuario atualizar(Long id, TipoUsuario tipoUsuario);
    void excluir(Long id);
}