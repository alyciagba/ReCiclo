package com.example.reciclo.controller;

import com.example.reciclo.model.Usuario;
import com.example.reciclo.service.UsuarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.Date;

@Controller
public class CadastroController {

    private final UsuarioService usuarioService;

    public CadastroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro() {
        return "categorias/cadastro";
    }

    @PostMapping("/salvar")
    public String processarCadastro(Usuario usuario, @RequestParam("confirmarSenha") String confirmarSenha, 
                                   Model model, RedirectAttributes redirectAttributes) {
        if (!usuario.getSenha().equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem");
            return "categorias/cadastro";
        }

        usuario.setTipoPerfil("USUARIO");

        try {
            usuarioService.salvarUsuario(usuario);
            redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso! Faça login para continuar.");
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
