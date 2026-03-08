package com.example.reciclo.mapper;

import com.example.reciclo.dto.UsuarioCadastroDTO;
import com.example.reciclo.dto.UsuarioResponseDTO;
import com.example.reciclo.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioCadastroDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setName(dto.getName());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCpf(dto.getCpf());
        usuario.setCelular(dto.getCelular());
        usuario.setGenero(dto.getGenero());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setCep(dto.getCep());
        usuario.setLogradouro(dto.getLogradouro());
        usuario.setNumero(dto.getNumero());
        usuario.setBairro(dto.getBairro());
        usuario.setMunicipio(dto.getMunicipio());
        usuario.setEstado(dto.getEstado());
        usuario.setComplemento(dto.getComplemento());
        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setName(usuario.getName());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setCelular(usuario.getCelular());
        dto.setGenero(usuario.getGenero());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setCep(usuario.getCep());
        dto.setLogradouro(usuario.getLogradouro());
        dto.setNumero(usuario.getNumero());
        dto.setBairro(usuario.getBairro());
        dto.setMunicipio(usuario.getMunicipio());
        dto.setEstado(usuario.getEstado());
        dto.setComplemento(usuario.getComplemento());
        dto.setTipoPerfil(usuario.getTipoPerfil());
        return dto;
    }
}
