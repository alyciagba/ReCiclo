package com.example.reciclo.config;

import com.example.reciclo.model.Usuario;
import com.example.reciclo.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UsuarioRepository usuarioRepository;

    public CustomAuthenticationSuccessHandler(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        setDefaultTargetUrl("/home");
        setAlwaysUseDefaultTargetUrl(true);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
                                       Authentication authentication) throws IOException, ServletException {

        String tipoPerfil = request.getParameter("tipoPerfil");
        String email = authentication.getName();

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            if (tipoPerfil != null && !tipoPerfil.isEmpty()) {
                usuario.setTipoPerfil(tipoPerfil);
                usuarioRepository.save(usuario);
            }

            request.getSession().setAttribute("usuarioLogado", email);
            request.getSession().setAttribute("tipoPerfil", usuario.getTipoPerfil());
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
