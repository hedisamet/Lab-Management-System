package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Publication;
import com.example.demo.repository.PublicationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublicationImpl implements IPublicationService {
    private PublicationRepository publicationRepository;

    @Override
    public Publication addPublication(Publication p) {
        return publicationRepository.save(p);
    }

    @Override
    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public Publication updatePublication(Publication p) {
        return publicationRepository.saveAndFlush(p);
    }

    @Override
    public Publication findPublication(Long id) {
        Publication p=(Publication) publicationRepository.findById(id).get();
        return p;
    }

    @Override
    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }

    @Override
    public List<Publication> findByType(String type) {
        return publicationRepository.findByType(type);
    }

    @Override
    public Publication findByTitre(String titre) {
        return publicationRepository.findByTitre(titre);
    }

    
}
