package com.pos.tech.challenge2.app.infra;

import com.pos.tech.challenge2.app.core.port.in.*;
import com.pos.tech.challenge2.app.core.port.out.*;
import com.pos.tech.challenge2.app.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {

    @Bean
    public UsuarioInputPort criarUsuarioInputPort(UsuarioOutputPort usuarioOutputPort,
                                                  SenhaCriptoOutputPort senhaCriptoOutputPort,
                                                  TipoUsuarioOutputPort tipoUsuarioOutputPort) {
        return new UsuarioUseCase(usuarioOutputPort, senhaCriptoOutputPort, tipoUsuarioOutputPort);
    }

    @Bean
    public TipoUsuarioInputPort criarTipoUsuarioInputPort(TipoUsuarioOutputPort tipoUsuarioOutputPort) {
        return new TipoUsuarioUseCase(tipoUsuarioOutputPort);
    }

    @Bean
    public RestauranteInputPort criarRestauranteInputPort(RestauranteOutputPort restauranteOutputPort,
                                                          UsuarioOutputPort usuarioOutputPort) {
        return new RestauranteUseCase(restauranteOutputPort, usuarioOutputPort);
    }

    @Bean
    public ItemCardapioInputPort criarItemCardapioInputPort(ItemCardapioOutputPort itemCardapioOutputPort,
                                                            RestauranteOutputPort restauranteOutputPort) {
        return new ItemCardapioUseCase(itemCardapioOutputPort, restauranteOutputPort);
    }
}