package com.example.reciclo.dto;

import java.util.Date;

public class UsuarioResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String celular;
    private String genero;
    private Date dataNascimento;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String municipio;
    private String estado;
    private String complemento;
    private String tipoPerfil;

    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(Long id, String name, String email, String tipoPerfil) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tipoPerfil = tipoPerfil;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public String getTipoPerfil() { return tipoPerfil; }
    public void setTipoPerfil(String tipoPerfil) { this.tipoPerfil = tipoPerfil; }
}
