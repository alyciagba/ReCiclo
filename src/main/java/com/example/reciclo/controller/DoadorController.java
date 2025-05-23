package com.example.reciclo.controller;

import com.example.reciclo.model.Item;
import com.example.reciclo.model.Usuario;
import com.example.reciclo.repository.UsuarioRepository;
import com.example.reciclo.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/doador")
public class DoadorController {

    private final ItemService itemService;
    private final UsuarioRepository usuarioRepository;

    public DoadorController(ItemService itemService, UsuarioRepository usuarioRepository) {
        this.itemService = itemService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public String paginaDoador(Model model, HttpSession session) {
        String email = (String) session.getAttribute("usuarioLogado");
        if (email == null) {
            return "redirect:/entrar";
        }

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null || !"doador".equals(usuario.getTipoPerfil())) {
            return "redirect:/home";
        }

        List<Item> itensDoUsuario = itemService.listarItensPorDoador(usuario);
        model.addAttribute("itens", itensDoUsuario);
        model.addAttribute("novoItem", new Item());
        
        return "categorias/doador";
    }

    @PostMapping("/cadastrar-item")
    public String cadastrarItem(Item item, HttpSession session, RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("usuarioLogado");
        if (email == null) {
            return "redirect:/entrar";
        }

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            return "redirect:/home";
        }

        item.setDoador(usuario);
        itemService.salvarItem(item);
        
        redirectAttributes.addFlashAttribute("mensagem", "Item cadastrado com sucesso!");
        return "redirect:/doador";
    }
}