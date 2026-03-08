package com.example.reciclo.dto;

import jakarta.validation.constraints.NotBlank;

public class ItemDTO {

    @NotBlank(message = "Nome do item é obrigatório")
    private String nomeItem;

    @NotBlank(message = "Estado do item é obrigatório")
    private String estadoItem;

    @NotBlank(message = "Tipo do item é obrigatório")
    private String tipoItem;

    @NotBlank(message = "Local de retirada é obrigatório")
    private String localRetirada;

    private Long doadorId;

    private Long pontoDeColetaId;

    public ItemDTO() {}

    public ItemDTO(String nomeItem, String estadoItem, String tipoItem, String localRetirada, Long doadorId) {
        this.nomeItem = nomeItem;
        this.estadoItem = estadoItem;
        this.tipoItem = tipoItem;
        this.localRetirada = localRetirada;
        this.doadorId = doadorId;
    }

    // Getters e Setters
    public String getNomeItem() { return nomeItem; }
    public void setNomeItem(String nomeItem) { this.nomeItem = nomeItem; }

    public String getEstadoItem() { return estadoItem; }
    public void setEstadoItem(String estadoItem) { this.estadoItem = estadoItem; }

    public String getTipoItem() { return tipoItem; }
    public void setTipoItem(String tipoItem) { this.tipoItem = tipoItem; }

    public String getLocalRetirada() { return localRetirada; }
    public void setLocalRetirada(String localRetirada) { this.localRetirada = localRetirada; }

    public Long getDoadorId() { return doadorId; }
    public void setDoadorId(Long doadorId) { this.doadorId = doadorId; }

    public Long getPontoDeColetaId() { return pontoDeColetaId; }
    public void setPontoDeColetaId(Long pontoDeColetaId) { this.pontoDeColetaId = pontoDeColetaId; }
}

