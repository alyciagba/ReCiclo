package com.example.reciclo.controller.rest;

import com.example.reciclo.dto.UsuarioCadastroDTO;
import com.example.reciclo.dto.UsuarioResponseDTO;
import com.example.reciclo.mapper.UsuarioMapper;
import com.example.reciclo.model.Usuario;
import com.example.reciclo.repository.UsuarioRepository;
import com.example.reciclo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Usuários", description = "Gerenciamento de usuários do sistema")
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
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    })
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
    @Operation(summary = "Obter usuário por ID", description = "Retorna um usuário específico pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok(usuarioMapper.toResponseDTO(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/usuarios/email/{email} - Obter usuário por email
     */
    @GetMapping("/email/{email}")
    @Operation(summary = "Obter usuário por email", description = "Busca um usuário pelo seu endereço de e-mail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
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
    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou senhas não coincidem"),
            @ApiResponse(responseCode = "409", description = "E-mail ou CPF já cadastrado")
    })
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
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar usuário")
    })
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
    @Operation(summary = "Deletar usuário", description = "Remove um usuário da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

