package com.example.reciclo.controller;

import com.example.reciclo.dto.UsuarioCadastroDTO;
import com.example.reciclo.mapper.UsuarioMapper;
import com.example.reciclo.model.Usuario;
import com.example.reciclo.dto.UsuarioResponseDTO;
import com.example.reciclo.service.UsuarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CadastroController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public CadastroController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("usuario", new UsuarioCadastroDTO());
        return "categorias/cadastro";
    }

    @PostMapping("/salvar")
    public String processarCadastro(
            @Valid @ModelAttribute("usuario") UsuarioCadastroDTO usuarioDto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "categorias/cadastro";
        }

        if (!usuarioDto.getSenha().equals(usuarioDto.getConfirmarSenha())) {
            model.addAttribute("erro", "As senhas não coincidem");
            return "categorias/cadastro";
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        usuario.setTipoPerfil("USUARIO");

        try {
            Usuario salvo = usuarioService.salvarUsuario(usuario);
            UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(salvo);
            redirectAttributes.addFlashAttribute(
                    "mensagem",
                    "Cadastro realizado com sucesso, " + responseDTO.getName() + "! Faça login para continuar."
            );
            return "redirect:/entrar";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("erro", "Já existe um usuário cadastrado com este e-mail ou CPF.");
            return "categorias/cadastro";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar usuário: " + e.getMessage());
            return "categorias/cadastro";
        }
    }
}
