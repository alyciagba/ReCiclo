package com.example.reciclo.dto;

import jakarta.validation.constraints.NotBlank;

public class PontoDeColetaDTO {

    @NotBlank(message = "Nome do ponto é obrigatório")
    private String nomePonto;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotBlank(message = "Tipo de ponto é obrigatório")
    private String tipoPonto;

    public PontoDeColetaDTO() {}

    public PontoDeColetaDTO(String nomePonto, String endereco, String tipoPonto) {
        this.nomePonto = nomePonto;
        this.endereco = endereco;
        this.tipoPonto = tipoPonto;
    }

    // Getters e Setters
    public String getNomePonto() { return nomePonto; }
    public void setNomePonto(String nomePonto) { this.nomePonto = nomePonto; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTipoPonto() { return tipoPonto; }
    public void setTipoPonto(String tipoPonto) { this.tipoPonto = tipoPonto; }
}

