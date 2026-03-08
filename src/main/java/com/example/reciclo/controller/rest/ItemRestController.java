package com.example.reciclo.controller.rest;

import com.example.reciclo.dto.ItemDTO;
import com.example.reciclo.dto.ItemResponseDTO;
import com.example.reciclo.mapper.ItemMapper;
import com.example.reciclo.model.Item;
import com.example.reciclo.model.Usuario;
import com.example.reciclo.repository.UsuarioRepository;
import com.example.reciclo.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ItemRestController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final UsuarioRepository usuarioRepository;

    public ItemRestController(ItemService itemService, ItemMapper itemMapper, UsuarioRepository usuarioRepository) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * GET /api/v1/items - Listar todos os itens
     */
    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> listarTodos() {
        List<Item> itens = itemService.listarTodosItens();
        List<ItemResponseDTO> dtos = itens.stream()
                .map(itemMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * GET /api/v1/items/{id} - Obter item por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> obterPorId(@PathVariable Long id) {
        Item item = itemService.buscarItemPorId(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemMapper.toResponseDTO(item));
    }

    /**
     * GET /api/v1/items/doador/{doadorId} - Listar itens de um doador
     */
    @GetMapping("/doador/{doadorId}")
    public ResponseEntity<List<ItemResponseDTO>> listarPorDoador(@PathVariable Long doadorId) {
        Usuario usuario = usuarioRepository.findById(doadorId).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Item> itens = itemService.listarItensPorDoador(usuario);
        List<ItemResponseDTO> dtos = itens.stream()
                .map(itemMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * POST /api/v1/items - Criar novo item
     */
    @PostMapping
    public ResponseEntity<ItemResponseDTO> criarItem(
            @Valid @RequestBody ItemDTO itemDTO,
            @RequestParam Long doadorId) {

        Usuario doador = usuarioRepository.findById(doadorId).orElse(null);
        if (doador == null) {
            return ResponseEntity.badRequest().build();
        }

        Item item = itemMapper.toEntity(itemDTO);
        item.setDoador(doador);
        Item itemSalvo = itemService.salvarItem(item);

        return ResponseEntity.status(HttpStatus.CREATED).body(itemMapper.toResponseDTO(itemSalvo));
    }

    /**
     * PUT /api/v1/items/{id} - Atualizar item existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> atualizarItem(
            @PathVariable Long id,
            @Valid @RequestBody ItemDTO itemDTO,
            @RequestParam Long doadorId) {

        Item itemExistente = itemService.buscarItemPorId(id);
        if (itemExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Verificar se o doador tem permissão
        if (!itemExistente.getDoador().getId().equals(doadorId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        itemExistente.setNomeItem(itemDTO.getNomeItem());
        itemExistente.setEstadoItem(itemDTO.getEstadoItem());
        itemExistente.setTipoItem(itemDTO.getTipoItem());
        itemExistente.setLocalRetirada(itemDTO.getLocalRetirada());

        Item itemAtualizado = itemService.salvarItem(itemExistente);
        return ResponseEntity.ok(itemMapper.toResponseDTO(itemAtualizado));
    }

    /**
     * DELETE /api/v1/items/{id} - Deletar item
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(
            @PathVariable Long id,
            @RequestParam Long doadorId) {

        Item item = itemService.buscarItemPorId(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        // Verificar se o doador tem permissão
        if (!item.getDoador().getId().equals(doadorId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        itemService.deletarItem(id, item.getDoador());
        return ResponseEntity.noContent().build();
    }
}

