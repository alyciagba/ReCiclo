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
    private String tipoPerfil;

    @Column
    private String preferenciasMateriais;

    @Column
    private String frequenciaDoacao;

    @Column
    private String tiposMaterialColetado;

    @Column
    private String raioAtuacao;

    @Column
    private String disponibilidade;

    @Column
    private String capacidadeColeta;

    public Perfil() {
    }

    public Perfil(Usuario usuario, String preferenciasMateriais, String frequenciaDoacao) {
        this.usuario = usuario;
        this.tipoPerfil = "DOADOR";
        this.preferenciasMateriais = preferenciasMateriais;
        this.frequenciaDoacao = frequenciaDoacao;
    }

    public Perfil(Usuario usuario, String tiposMaterialColetado, String raioAtuacao,
                 String disponibilidade, String capacidadeColeta) {
        this.usuario = usuario;
        this.tipoPerfil = "COLETOR";
        this.tiposMaterialColetado = tiposMaterialColetado;
        this.raioAtuacao = raioAtuacao;
        this.disponibilidade = disponibilidade;
        this.capacidadeColeta = capacidadeColeta;
    }

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
