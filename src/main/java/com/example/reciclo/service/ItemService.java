package com.example.reciclo.service;

import com.example.reciclo.model.Item;
import com.example.reciclo.model.Usuario;
import com.example.reciclo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item salvarItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> listarTodosItens() {
        return itemRepository.findAll();
    }

    public List<Item> listarItensPorDoador(Usuario doador) {
        return itemRepository.findByDoador(doador);
    }

    public Item buscarItemPorId(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public void excluirItem(Long id) {
        itemRepository.deleteById(id);
    }
}