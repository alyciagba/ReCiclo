package com.example.reciclo.repository;

import com.example.reciclo.model.PontoDeColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PontoDeColetaRepository extends JpaRepository<PontoDeColeta, Long> {
    List<PontoDeColeta> findByTipoPonto(String tipoPonto);
}

