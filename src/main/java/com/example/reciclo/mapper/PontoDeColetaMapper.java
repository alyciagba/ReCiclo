package com.example.reciclo.mapper;

import com.example.reciclo.dto.PontoDeColetaDTO;
import com.example.reciclo.dto.PontoDeColetaResponseDTO;
import com.example.reciclo.model.PontoDeColeta;
import org.springframework.stereotype.Component;

@Component
public class PontoDeColetaMapper {

    public PontoDeColeta toEntity(PontoDeColetaDTO dto) {
        if (dto == null) return null;

        PontoDeColeta ponto = new PontoDeColeta();
        ponto.setNomePonto(dto.getNomePonto());
        ponto.setEndereco(dto.getEndereco());
        ponto.setTipoPonto(dto.getTipoPonto());
        return ponto;
    }

    public PontoDeColetaResponseDTO toResponseDTO(PontoDeColeta ponto) {
        if (ponto == null) return null;

        return new PontoDeColetaResponseDTO(
                ponto.getId(),
                ponto.getNomePonto(),
                ponto.getEndereco(),
                ponto.getTipoPonto()
        );
    }

    public PontoDeColetaDTO toDTO(PontoDeColeta ponto) {
        if (ponto == null) return null;

        return new PontoDeColetaDTO(
                ponto.getNomePonto(),
                ponto.getEndereco(),
                ponto.getTipoPonto()
        );
    }
}

