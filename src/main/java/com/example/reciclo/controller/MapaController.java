package com.example.reciclo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapaController {

    @GetMapping("/mapa")
    public String mapa() {
        return "categorias/mapa";
    }
}