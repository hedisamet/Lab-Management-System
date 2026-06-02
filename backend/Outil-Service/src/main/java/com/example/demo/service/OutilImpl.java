package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Outil;
import com.example.demo.repository.OutilRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutilImpl implements IOutilService {
    private final OutilRepository outilRepository;

    @Override
    public Outil addOutil(Outil o) {
        return outilRepository.save(o);
    }

    @Override
    public void deleteOutil(Long id) {
        outilRepository.deleteById(id);
    }

    @Override
    public Outil updateOutil(Outil o) {
        return outilRepository.save(o); // Pas besoin de `saveAndFlush` ici
    }

    @Override
    public Outil findOutil(Long id) {
        return outilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Outil non trouvé avec l'id : " + id));
    }

    @Override
    public List<Outil> findAll() {
        return outilRepository.findAll();
    }

    @Override
    public List<Outil> findBySource(String source) {
        return outilRepository.findBySource(source);
    }
}
