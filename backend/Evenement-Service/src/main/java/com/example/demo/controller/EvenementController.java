package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Evenement;
import com.example.demo.service.IEvenementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/evenements")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EvenementController {

    private final IEvenementService evenementService;

    @GetMapping
    public ResponseEntity<List<Evenement>> getAllEvenements() {
        return ResponseEntity.ok(evenementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evenement> getEvenementById(@PathVariable Long id) {
        return ResponseEntity.ok(evenementService.findEvenement(id));
    }

    @GetMapping("/titre/{titre}")
    public ResponseEntity<Evenement> getEvenementByTitre(@PathVariable String titre) {
        return ResponseEntity.ok(evenementService.findByTitre(titre));
    }

    @GetMapping("/lieu/{lieu}")
    public ResponseEntity<List<Evenement>> getEvenementsByLieu(@PathVariable String lieu) {
        return ResponseEntity.ok(evenementService.findByLieu(lieu));
    }

    @PostMapping
    public ResponseEntity<Evenement> addEvenement(@RequestBody Evenement evenement) {
        return new ResponseEntity<>(evenementService.addEvenement(evenement), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evenement> updateEvenement(@PathVariable Long id, @RequestBody Evenement evenement) {
        evenement.setId(id);
        return ResponseEntity.ok(evenementService.updateEvenement(evenement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvenement(@PathVariable Long id) {
        evenementService.deleteEvenement(id);
        return ResponseEntity.noContent().build();
    }
}
