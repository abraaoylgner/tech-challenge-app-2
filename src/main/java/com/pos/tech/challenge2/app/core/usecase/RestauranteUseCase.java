package com.pos.tech.challenge2.app.core.usecase;

import com.pos.tech.challenge2.app.core.domain.Restaurante;
import com.pos.tech.challenge2.app.core.domain.TipoUsuario;
import com.pos.tech.challenge2.app.core.port.in.RestauranteInputPort;
import com.pos.tech.challenge2.app.core.port.out.RestauranteOutputPort;
import com.pos.tech.challenge2.app.core.port.out.UsuarioOutputPort;

import java.util.List;

public class RestauranteUseCase implements RestauranteInputPort {

    private final RestauranteOutputPort restauranteOutputPort;
    private final UsuarioOutputPort usuarioOutputPort;

    public RestauranteUseCase(RestauranteOutputPort restauranteOutputPort, UsuarioOutputPort usuarioOutputPort) {
        this.restauranteOutputPort = restauranteOutputPort;
        this.usuarioOutputPort = usuarioOutputPort;
    }

    @Override
    public Restaurante criar(Restaurante restaurante) {
        var dono = usuarioOutputPort.buscarPorId(restaurante.getIdDono())
                .orElseThrow(() -> new IllegalArgumentException("Dono não encontrado com o ID fornecido."));

        if (!TipoUsuario.DONO_RESTAURANTE.equals(dono.getTipoUsuario())) {
            throw new IllegalArgumentException("O usuário responsável precisa ser do tipo DONO_RESTAURANTE.");
        }

        return restauranteOutputPort.salvar(restaurante);
    }

    @Override
    public Restaurante buscarPorId(Long id) {
        return restauranteOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado."));
    }

    @Override
    public List<Restaurante> buscarTodos() {
        return restauranteOutputPort.buscarTodos();
    }
}