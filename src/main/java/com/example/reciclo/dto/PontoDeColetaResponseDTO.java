package com.example.reciclo.dto;

public class PontoDeColetaResponseDTO {

    private Long id;
    private String nomePonto;
    private String endereco;
    private String tipoPonto;

    public PontoDeColetaResponseDTO() {}

    public PontoDeColetaResponseDTO(Long id, String nomePonto, String endereco, String tipoPonto) {
        this.id = id;
        this.nomePonto = nomePonto;
        this.endereco = endereco;
        this.tipoPonto = tipoPonto;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomePonto() { return nomePonto; }
    public void setNomePonto(String nomePonto) { this.nomePonto = nomePonto; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTipoPonto() { return tipoPonto; }
    public void setTipoPonto(String tipoPonto) { this.tipoPonto = tipoPonto; }
}

