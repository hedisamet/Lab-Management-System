package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Outil;

public interface OutilRepository extends JpaRepository<Outil, Long> {
    List<Outil> findBySource(String source); // Retourne une liste
}
