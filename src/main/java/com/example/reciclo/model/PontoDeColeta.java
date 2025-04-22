package com.example.reciclo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PontoDeColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomePonto;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String tipoPonto;

    @OneToMany(mappedBy = "pontoDeColeta")
    private List<Item> itens;

    public PontoDeColeta() {}

    public PontoDeColeta(String nomePonto, String endereco, String tipoPonto) {
        this.nomePonto = nomePonto;
        this.endereco = endereco;
        this.tipoPonto = tipoPonto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePonto() {
        return nomePonto;
    }

    public void setNomePonto(String nomePonto) {
        this.nomePonto = nomePonto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoPonto() {
        return tipoPonto;
    }

    public void setTipoPonto(String tipoPonto) {
        this.tipoPonto = tipoPonto;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}