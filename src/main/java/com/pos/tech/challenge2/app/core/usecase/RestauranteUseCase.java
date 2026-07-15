package com.pos.tech.challenge2.app.core.usecase;

import com.pos.tech.challenge2.app.core.domain.Restaurante;
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
        validarDonoRestaurante(restaurante.getIdDono());
        return restauranteOutputPort.salvar(restaurante);
    }

    @Override
    public Restaurante buscarPorId(Long id) {
        return restauranteOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado com o ID fornecido."));
    }

    @Override
    public List<Restaurante> buscarTodos() {
        return restauranteOutputPort.buscarTodos();
    }

    @Override
    public Restaurante atualizar(Long id, Restaurante restauranteAtualizado) {
        var restauranteExistente = buscarPorId(id);

        if (!restauranteExistente.getIdDono().equals(restauranteAtualizado.getIdDono())) {
            validarDonoRestaurante(restauranteAtualizado.getIdDono());
        }

        restauranteExistente.setNome(restauranteAtualizado.getNome());
        restauranteExistente.setEndereco(restauranteAtualizado.getEndereco());
        restauranteExistente.setTipoCozinha(restauranteAtualizado.getTipoCozinha());
        restauranteExistente.setHorarioFuncionamento(restauranteAtualizado.getHorarioFuncionamento());
        restauranteExistente.setIdDono(restauranteAtualizado.getIdDono());

        return restauranteOutputPort.salvar(restauranteExistente);
    }

    @Override
    public void excluir(Long id) {
        buscarPorId(id);
        restauranteOutputPort.excluir(id);
    }

    private void validarDonoRestaurante(Long idDono) {
        var dono = usuarioOutputPort.buscarPorId(idDono)
                .orElseThrow(() -> new IllegalArgumentException("Dono não encontrado com o ID fornecido."));

        if (!"DONO_RESTAURANTE".equalsIgnoreCase(dono.getTipoUsuario().getNome())) {
            throw new IllegalArgumentException("O usuário responsável precisa ser do tipo DONO_RESTAURANTE.");
        }
    }
}