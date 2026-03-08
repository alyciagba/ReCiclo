package com.example.reciclo.mapper;

import com.example.reciclo.dto.ItemDTO;
import com.example.reciclo.dto.ItemResponseDTO;
import com.example.reciclo.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item toEntity(ItemDTO dto) {
        if (dto == null) return null;

        Item item = new Item();
        item.setNomeItem(dto.getNomeItem());
        item.setEstadoItem(dto.getEstadoItem());
        item.setTipoItem(dto.getTipoItem());
        item.setLocalRetirada(dto.getLocalRetirada());
        return item;
    }

    public ItemResponseDTO toResponseDTO(Item item) {
        if (item == null) return null;

        ItemResponseDTO dto = new ItemResponseDTO();
        dto.setId(item.getId());
        dto.setNomeItem(item.getNomeItem());
        dto.setEstadoItem(item.getEstadoItem());
        dto.setTipoItem(item.getTipoItem());
        dto.setLocalRetirada(item.getLocalRetirada());

        if (item.getDoador() != null) {
            dto.setDoadorId(item.getDoador().getId());
            dto.setDoadorNome(item.getDoador().getName());
        }

        if (item.getPontoDeColeta() != null) {
            dto.setPontoDeColetaId(item.getPontoDeColeta().getId());
            dto.setPontoDeColetaNome(item.getPontoDeColeta().getNomePonto());
        }

        return dto;
    }

    public ItemDTO toDTO(Item item) {
        if (item == null) return null;

        ItemDTO dto = new ItemDTO();
        dto.setNomeItem(item.getNomeItem());
        dto.setEstadoItem(item.getEstadoItem());
        dto.setTipoItem(item.getTipoItem());
        dto.setLocalRetirada(item.getLocalRetirada());

        if (item.getDoador() != null) {
            dto.setDoadorId(item.getDoador().getId());
        }

        if (item.getPontoDeColeta() != null) {
            dto.setPontoDeColetaId(item.getPontoDeColeta().getId());
        }

        return dto;
    }
}

