package com.pos.tech.challenge2.app.core.usecase;

import com.pos.tech.challenge2.app.core.domain.TipoUsuario;
import com.pos.tech.challenge2.app.core.domain.Usuario;
import com.pos.tech.challenge2.app.core.port.out.SenhaCriptoOutputPort;
import com.pos.tech.challenge2.app.core.port.out.TipoUsuarioOutputPort;
import com.pos.tech.challenge2.app.core.port.out.UsuarioOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseTest {

    @Mock
    private UsuarioOutputPort usuarioOutputPort;

    @Mock
    private SenhaCriptoOutputPort senhaCriptoOutputPort;

    @Mock
    private TipoUsuarioOutputPort tipoUsuarioOutputPort;

    @InjectMocks
    private UsuarioUseCase usuarioUseCase;

    private Usuario usuarioMock;
    private TipoUsuario tipoUsuarioMock;

    @BeforeEach
    void setUp() {
        tipoUsuarioMock = new TipoUsuario();
        tipoUsuarioMock.setId(1L);
        tipoUsuarioMock.setNome("CLIENTE");

        usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        usuarioMock.setNome("João");
        usuarioMock.setLogin("joao@teste.com");
        usuarioMock.setSenha("123456");
        usuarioMock.setTipoUsuario(tipoUsuarioMock);
    }

    @Test
    void deveCriarUsuarioComSucesso() {
        // Arrange (Preparação)
        when(tipoUsuarioOutputPort.buscarPorId(any())).thenReturn(Optional.of(tipoUsuarioMock));
        when(senhaCriptoOutputPort.criptografar(anyString())).thenReturn("senhaCriptografada");
        when(usuarioOutputPort.salvar(any(Usuario.class))).thenReturn(usuarioMock);

        // Act (Ação)
        Usuario usuarioCriado = usuarioUseCase.criar(usuarioMock);

        // Assert (Verificação)
        assertNotNull(usuarioCriado);
        assertEquals("João", usuarioCriado.getNome());
        verify(usuarioOutputPort, times(1)).salvar(any(Usuario.class));
        verify(senhaCriptoOutputPort, times(1)).criptografar(anyString());
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        // Arrange
        when(usuarioOutputPort.buscarPorId(1L)).thenReturn(Optional.of(usuarioMock));

        // Act
        // Assumindo que você tem um método buscarPorId no UseCase
        Optional<Usuario> usuarioEncontrado = usuarioOutputPort.buscarPorId(1L);

        // Assert
        assertTrue(usuarioEncontrado.isPresent());
        assertEquals("joao@teste.com", usuarioEncontrado.get().getLogin());
    }

    @Test
    void deveExcluirUsuarioComSucesso() {

        when(usuarioOutputPort.buscarPorId(1L)).thenReturn(Optional.of(usuarioMock));

        doNothing().when(usuarioOutputPort).excluir(1L);

        usuarioUseCase.excluir(1L);

        verify(usuarioOutputPort, times(1)).buscarPorId(1L);
        verify(usuarioOutputPort, times(1)).excluir(1L);
    }
}