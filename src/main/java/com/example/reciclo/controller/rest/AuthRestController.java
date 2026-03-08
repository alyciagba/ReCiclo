package com.example.reciclo.controller.rest;

import com.example.reciclo.dto.LoginRequestDTO;
import com.example.reciclo.dto.LoginResponseDTO;
import com.example.reciclo.model.Usuario;
import com.example.reciclo.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Autenticação", description = "Endpoints de autenticação e login")
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
    @Operation(summary = "Autenticar usuário", description = "Realiza login de um usuário com e-mail e senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida"),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos")
    })
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

