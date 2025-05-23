package com.example.reciclo.controller;

import com.example.reciclo.model.Item;
import com.example.reciclo.model.Usuario;
import com.example.reciclo.repository.UsuarioRepository;
import com.example.reciclo.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/coletor")
public class ColetorController {

    private final ItemService itemService;
    private final UsuarioRepository usuarioRepository;

    public ColetorController(ItemService itemService, UsuarioRepository usuarioRepository) {
        this.itemService = itemService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public String paginaColetor(Model model, HttpSession session) {
        String email = (String) session.getAttribute("usuarioLogado");
        if (email == null) {
            return "redirect:/entrar";
        }

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null || !"coletor".equals(usuario.getTipoPerfil())) {
            return "redirect:/home";
        }

        List<Item> todosItens = itemService.listarTodosItens();
        model.addAttribute("itens", todosItens);
        
        return "categorias/coletor";
    }
}