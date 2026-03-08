package com.example.reciclo.controller.rest;

import com.example.reciclo.dto.UsuarioCadastroDTO;
import com.example.reciclo.dto.UsuarioResponseDTO;
import com.example.reciclo.mapper.UsuarioMapper;
import com.example.reciclo.model.Usuario;
import com.example.reciclo.repository.UsuarioRepository;
import com.example.reciclo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioRestController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRestController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    /**
     * GET /api/v1/usuarios - Listar todos os usuários (dados públicos apenas)
     */
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> dtos = usuarios.stream()
                .map(usuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * GET /api/v1/usuarios/{id} - Obter usuário por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok(usuarioMapper.toResponseDTO(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/usuarios/email/{email} - Obter usuário por email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponseDTO> obterPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioMapper.toResponseDTO(usuario));
    }

    /**
     * POST /api/v1/usuarios - Registrar novo usuário
     */
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioCadastroDTO usuarioDto) {
        // Validar se as senhas coincidem
        if (!usuarioDto.getSenha().equals(usuarioDto.getConfirmarSenha())) {
            return ResponseEntity.badRequest().body("As senhas não coincidem");
        }

        try {
            Usuario usuario = usuarioMapper.toEntity(usuarioDto);
            usuario.setTipoPerfil("USUARIO");
            Usuario salvo = usuarioService.salvarUsuario(usuario);
            UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um usuário com este e-mail ou CPF");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    /**
     * PUT /api/v1/usuarios/{id} - Atualizar usuário
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioCadastroDTO usuarioDto) {

        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setName(usuarioDto.getName());
                    usuario.setCelular(usuarioDto.getCelular());
                    usuario.setGenero(usuarioDto.getGenero());
                    usuario.setDataNascimento(usuarioDto.getDataNascimento());
                    usuario.setCep(usuarioDto.getCep());
                    usuario.setLogradouro(usuarioDto.getLogradouro());
                    usuario.setNumero(usuarioDto.getNumero());
                    usuario.setBairro(usuarioDto.getBairro());
                    usuario.setMunicipio(usuarioDto.getMunicipio());
                    usuario.setEstado(usuarioDto.getEstado());
                    usuario.setComplemento(usuarioDto.getComplemento());

                    try {
                        Usuario atualizado = usuarioRepository.save(usuario);
                        return ResponseEntity.ok(usuarioMapper.toResponseDTO(atualizado));
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Erro ao atualizar usuário: " + e.getMessage());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/v1/usuarios/{id} - Deletar usuário
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

