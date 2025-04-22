package com.example.reciclo.controller;

import com.example.reciclo.model.Usuario;
import com.example.reciclo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro() {
        return "categorias/cadastro";
    }

    @PostMapping("/salvar")
    public String processarCadastro(Usuario usuario, Model model) {
        usuarioService.salvarUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "categorias/cadastro_sucesso";
    }
}


