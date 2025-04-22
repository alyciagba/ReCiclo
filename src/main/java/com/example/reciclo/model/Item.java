package com.example.reciclo.model;

import jakarta.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeItem;

    @Column(nullable = false)
    private String estadoItem;

    @Column(nullable = false)
    private String tipoItem;

    @Column(nullable = false)
    private String localRetirada;

    @ManyToOne
    @JoinColumn(name = "id_doador", nullable = false)
    private Usuario doador;

    @ManyToOne
    @JoinColumn(name = "id_ponto_coleta", nullable = true)
    private PontoDeColeta pontoDeColeta;

    public Item() {}

    public Item(String nomeItem, String estadoItem, String tipoItem, String localRetirada, Usuario doador) {
        this.nomeItem = nomeItem;
        this.estadoItem = estadoItem;
        this.tipoItem = tipoItem;
        this.localRetirada = localRetirada;
        this.doador = doador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getEstadoItem() {
        return estadoItem;
    }

    public void setEstadoItem(String estadoItem) {
        this.estadoItem = estadoItem;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getLocalRetirada() {
        return localRetirada;
    }

    public void setLocalRetirada(String localRetirada) {
        this.localRetirada = localRetirada;
    }

    public Usuario getDoador() {
        return doador;
    }

    public void setDoador(Usuario doador) {
        this.doador = doador;
    }

    public PontoDeColeta getPontoDeColeta() {
        return pontoDeColeta;
    }

    public void setPontoDeColeta(PontoDeColeta pontoDeColeta) {
        this.pontoDeColeta = pontoDeColeta;
    }
}