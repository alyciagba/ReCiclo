package com.example.reciclo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CadastroUsuarioController {

    @GetMapping("/cadastro-usuario")
    public String cadastro() {
        return "cadastroUsuario";
    }
}
