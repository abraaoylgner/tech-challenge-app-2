package com.pos.tech.challenge2.app.core.port.out;

import com.pos.tech.challenge2.app.core.domain.Usuario;
import java.util.Optional;

public interface UsuarioOutputPort {
    Optional<Usuario> buscarPorId(Long id);
}