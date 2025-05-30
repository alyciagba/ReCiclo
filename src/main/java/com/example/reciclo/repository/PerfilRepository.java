package com.example.reciclo.repository;

import com.example.reciclo.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByUsuarioId(Long usuarioId);
}
