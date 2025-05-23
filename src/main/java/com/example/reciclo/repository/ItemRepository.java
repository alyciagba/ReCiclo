package com.example.reciclo.repository;

import com.example.reciclo.model.Item;
import com.example.reciclo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByDoador(Usuario doador);
    List<Item> findAll();
}