package com.pos.tech.challenge2.app.infra.security;

import com.pos.tech.challenge2.app.core.port.out.UsuarioOutputPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioOutputPort usuarioOutputPort;

    public SecurityFilter(TokenService tokenService, UsuarioOutputPort usuarioOutputPort) {
        this.tokenService = tokenService;
        this.usuarioOutputPort = usuarioOutputPort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            var login = tokenService.getSubject(tokenJWT);

            if (login != null) {
                var usuario = usuarioOutputPort.buscarPorLogin(login)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getTipoUsuario().getNome()));

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}