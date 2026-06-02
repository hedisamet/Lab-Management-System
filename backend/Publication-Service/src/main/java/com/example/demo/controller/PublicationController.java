package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Publication;
import com.example.demo.service.IPublicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/publications")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PublicationController {

    private final IPublicationService publicationService;

    @GetMapping
    public ResponseEntity<List<Publication>> getAllPublications() {
        return ResponseEntity.ok(publicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublicationById(@PathVariable Long id) {
        return ResponseEntity.ok(publicationService.findPublication(id));
    }

    @GetMapping("/titre/{titre}")
    public ResponseEntity<Publication> getPublicationByTitre(@PathVariable String titre) {
        return ResponseEntity.ok(publicationService.findByTitre(titre));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Publication>> getPublicationsByType(@PathVariable String type) {
        return ResponseEntity.ok(publicationService.findByType(type));
    }

    @PostMapping
    public ResponseEntity<Publication> addPublication(@RequestBody Publication publication) {
        return new ResponseEntity<>(publicationService.addPublication(publication), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable Long id, @RequestBody Publication publication) {
        publication.setId(id);
        return ResponseEntity.ok(publicationService.updatePublication(publication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        publicationService.deletePublication(id);
        return ResponseEntity.noContent().build();
    }
}
