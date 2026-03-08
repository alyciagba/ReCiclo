package com.example.reciclo.dto;

public class ItemResponseDTO {

    private Long id;
    private String nomeItem;
    private String estadoItem;
    private String tipoItem;
    private String localRetirada;
    private Long doadorId;
    private String doadorNome;
    private Long pontoDeColetaId;
    private String pontoDeColetaNome;

    public ItemResponseDTO() {}

    public ItemResponseDTO(Long id, String nomeItem, String estadoItem, String tipoItem,
                          String localRetirada, Long doadorId, String doadorNome) {
        this.id = id;
        this.nomeItem = nomeItem;
        this.estadoItem = estadoItem;
        this.tipoItem = tipoItem;
        this.localRetirada = localRetirada;
        this.doadorId = doadorId;
        this.doadorNome = doadorNome;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public String getDoadorNome() { return doadorNome; }
    public void setDoadorNome(String doadorNome) { this.doadorNome = doadorNome; }

    public Long getPontoDeColetaId() { return pontoDeColetaId; }
    public void setPontoDeColetaId(Long pontoDeColetaId) { this.pontoDeColetaId = pontoDeColetaId; }

    public String getPontoDeColetaNome() { return pontoDeColetaNome; }
    public void setPontoDeColetaNome(String pontoDeColetaNome) { this.pontoDeColetaNome = pontoDeColetaNome; }
}

