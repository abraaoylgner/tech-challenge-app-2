package com.pos.tech.challenge2.app.core.usecase;

import com.pos.tech.challenge2.app.core.domain.TipoUsuario;
import com.pos.tech.challenge2.app.core.port.in.TipoUsuarioInputPort;
import com.pos.tech.challenge2.app.core.port.out.TipoUsuarioOutputPort;
import java.util.List;

public class TipoUsuarioUseCase implements TipoUsuarioInputPort {

    private final TipoUsuarioOutputPort outputPort;

    public TipoUsuarioUseCase(TipoUsuarioOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public TipoUsuario criar(TipoUsuario tipoUsuario) {
        return outputPort.salvar(tipoUsuario);
    }

    @Override
    public TipoUsuario buscarPorId(Long id) {
        return outputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado."));
    }

    @Override
    public List<TipoUsuario> buscarTodos() {
        return outputPort.buscarTodos();
    }

    @Override
    public TipoUsuario atualizar(Long id, TipoUsuario tipoUsuario) {
        var existente = buscarPorId(id);
        existente.setNome(tipoUsuario.getNome());
        return outputPort.salvar(existente);
    }

    @Override
    public void excluir(Long id) {
        buscarPorId(id);
        outputPort.excluir(id);
    }
}