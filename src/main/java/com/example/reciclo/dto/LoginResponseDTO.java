package com.example.reciclo.dto;

public class LoginResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String tipoPerfil;
    private String token;

    public LoginResponseDTO() {}

    public LoginResponseDTO(Long id, String name, String email, String tipoPerfil) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tipoPerfil = tipoPerfil;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTipoPerfil() { return tipoPerfil; }
    public void setTipoPerfil(String tipoPerfil) { this.tipoPerfil = tipoPerfil; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}

