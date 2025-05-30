package com.example.reciclo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String tipoPerfil; // "DOADOR" ou "COLETOR"

    // Campos específicos para DOADOR
    @Column
    private String preferenciasMateriais; // ex: "plástico, papel, vidro"

    @Column
    private String frequenciaDoacao; // ex: "semanal", "mensal"

    // Campos específicos para COLETOR
    @Column
    private String tiposMaterialColetado; // ex: "plástico, papel, vidro"

    @Column
    private String raioAtuacao; // em km

    @Column
    private String disponibilidade; // ex: "seg-sex, 9h-18h"

    @Column
    private String capacidadeColeta; // quantidade/volume que pode coletar

    // Construtor vazio necessário para JPA
    public Perfil() {
    }

    // Construtor para perfil DOADOR
    public Perfil(Usuario usuario, String preferenciasMateriais, String frequenciaDoacao) {
        this.usuario = usuario;
        this.tipoPerfil = "DOADOR";
        this.preferenciasMateriais = preferenciasMateriais;
        this.frequenciaDoacao = frequenciaDoacao;
    }

    // Construtor para perfil COLETOR
    public Perfil(Usuario usuario, String tiposMaterialColetado, String raioAtuacao,
                 String disponibilidade, String capacidadeColeta) {
        this.usuario = usuario;
        this.tipoPerfil = "COLETOR";
        this.tiposMaterialColetado = tiposMaterialColetado;
        this.raioAtuacao = raioAtuacao;
        this.disponibilidade = disponibilidade;
        this.capacidadeColeta = capacidadeColeta;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public String getPreferenciasMateriais() {
        return preferenciasMateriais;
    }

    public void setPreferenciasMateriais(String preferenciasMateriais) {
        this.preferenciasMateriais = preferenciasMateriais;
    }

    public String getFrequenciaDoacao() {
        return frequenciaDoacao;
    }

    public void setFrequenciaDoacao(String frequenciaDoacao) {
        this.frequenciaDoacao = frequenciaDoacao;
    }

    public String getTiposMaterialColetado() {
        return tiposMaterialColetado;
    }

    public void setTiposMaterialColetado(String tiposMaterialColetado) {
        this.tiposMaterialColetado = tiposMaterialColetado;
    }

    public String getRaioAtuacao() {
        return raioAtuacao;
    }

    public void setRaioAtuacao(String raioAtuacao) {
        this.raioAtuacao = raioAtuacao;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getCapacidadeColeta() {
        return capacidadeColeta;
    }

    public void setCapacidadeColeta(String capacidadeColeta) {
        this.capacidadeColeta = capacidadeColeta;
    }
}
