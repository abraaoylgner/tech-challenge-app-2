package com.pos.tech.challenge2.app.core.usecase;

import com.pos.tech.challenge2.app.core.domain.Usuario;
import com.pos.tech.challenge2.app.core.port.in.UsuarioInputPort;
import com.pos.tech.challenge2.app.core.port.out.SenhaCriptoOutputPort;
import com.pos.tech.challenge2.app.core.port.out.TipoUsuarioOutputPort; // Nova importação
import com.pos.tech.challenge2.app.core.port.out.UsuarioOutputPort;

import java.time.LocalDateTime;
import java.util.List;

public class UsuarioUseCase implements UsuarioInputPort {

    private final UsuarioOutputPort usuarioOutputPort;
    private final SenhaCriptoOutputPort senhaCriptoOutputPort;
    private final TipoUsuarioOutputPort tipoUsuarioOutputPort;

    public UsuarioUseCase(UsuarioOutputPort usuarioOutputPort,
                          SenhaCriptoOutputPort senhaCriptoOutputPort,
                          TipoUsuarioOutputPort tipoUsuarioOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
        this.senhaCriptoOutputPort = senhaCriptoOutputPort;
        this.tipoUsuarioOutputPort = tipoUsuarioOutputPort;
    }

    @Override
    public Usuario criar(Usuario usuario) {
        // Valida se o tipo de usuário existe antes de prosseguir
        var tipoUsuario = tipoUsuarioOutputPort.buscarPorId(usuario.getTipoUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado com o ID: " + usuario.getTipoUsuario().getId()));

        usuario.setTipoUsuario(tipoUsuario);

        String senhaCriptografada = senhaCriptoOutputPort.criptografar(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioOutputPort.salvar(usuario);
    }

    @Override
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        // Valida o novo tipo de usuário se ele foi alterado
        var novoTipo = tipoUsuarioOutputPort.buscarPorId(usuarioAtualizado.getTipoUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado com o ID: " + usuarioAtualizado.getTipoUsuario().getId()));

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setTipoUsuario(novoTipo);
        usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());
        usuarioExistente.setDataUltimaAlteracao(LocalDateTime.now());

        return usuarioOutputPort.salvar(usuarioExistente);
    }

    @Override
    public List<Usuario> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome para busca não pode estar vazio.");
        }
        return usuarioOutputPort.buscarPorNome(nome);
    }

    @Override
    public void excluir(Long id) {
        Usuario usuarioExistente = usuarioOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        usuarioOutputPort.excluir(usuarioExistente.getId());
    }

    @Override
    public void alterarSenha(Long id, String novaSenha) {
        Usuario usuarioExistente = usuarioOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        // Criptografa a nova senha antes de salvar
        usuarioExistente.setSenha(senhaCriptoOutputPort.criptografar(novaSenha));
        usuarioExistente.setDataUltimaAlteracao(LocalDateTime.now());

        usuarioOutputPort.salvar(usuarioExistente);
    }
}