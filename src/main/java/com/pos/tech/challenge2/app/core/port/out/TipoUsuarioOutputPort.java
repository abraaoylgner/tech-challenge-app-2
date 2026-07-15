package com.pos.tech.challenge2.app.core.port.out;
import com.pos.tech.challenge2.app.core.domain.TipoUsuario;
import java.util.List;
import java.util.Optional;

public interface TipoUsuarioOutputPort {
    TipoUsuario salvar(TipoUsuario tipoUsuario);
    Optional<TipoUsuario> buscarPorId(Long id);
    List<TipoUsuario> buscarTodos();
    void excluir(Long id);
}