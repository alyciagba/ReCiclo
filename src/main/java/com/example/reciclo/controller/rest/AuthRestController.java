package com.example.reciclo.controller.rest;

import com.example.reciclo.dto.LoginRequestDTO;
import com.example.reciclo.dto.LoginResponseDTO;
import com.example.reciclo.model.Usuario;
import com.example.reciclo.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthRestController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthRestController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * POST /api/v1/auth/login - Autenticar usuário
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário ou senha inválidos");
        }

        if (!passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário ou senha inválidos");
        }

        LoginResponseDTO response = new LoginResponseDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getTipoPerfil()
        );

        return ResponseEntity.ok(response);
    }
}

