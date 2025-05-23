package com.example.reciclo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/entrar")
    public String mostrarFormularioLogin(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            @RequestParam(required = false) String loginRequired,
            @RequestParam(required = false) String logoutSuccess,
            Model model) {

        if (error != null) {
            model.addAttribute("erro", "Credenciais inválidas");
        }

        if (loginRequired != null) {
            model.addAttribute("loginRequired", "Você precisa fazer login para acessar esta página");
        }

        if (logoutSuccess != null) {
            model.addAttribute("mensagem", "Logout realizado com sucesso");
        }

        return "categorias/login";
    }


}
