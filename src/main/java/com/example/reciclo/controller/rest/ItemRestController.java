package com.example.reciclo.controller.rest;

import com.example.reciclo.dto.ItemDTO;
import com.example.reciclo.dto.ItemResponseDTO;
import com.example.reciclo.mapper.ItemMapper;
import com.example.reciclo.model.Item;
import com.example.reciclo.model.Usuario;
import com.example.reciclo.repository.UsuarioRepository;
import com.example.reciclo.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Items", description = "Gerenciamento de itens para doação")
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
    @Operation(summary = "Listar todos os itens", description = "Retorna uma lista de todos os itens cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de itens retornada com sucesso")
    })
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
    @Operation(summary = "Obter item por ID", description = "Retorna um item específico pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
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
    @Operation(summary = "Listar itens por doador", description = "Retorna todos os itens de um doador específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de itens do doador retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Doador não encontrado")
    })
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
    @Operation(summary = "Criar novo item", description = "Cria um novo item de doação no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou doador não encontrado")
    })
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
    @Operation(summary = "Atualizar item", description = "Atualiza os dados de um item existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado"),
            @ApiResponse(responseCode = "403", description = "Sem permissão para atualizar este item")
    })
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
    @Operation(summary = "Deletar item", description = "Remove um item da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado"),
            @ApiResponse(responseCode = "403", description = "Sem permissão para deletar este item")
    })
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

