package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Outil;
import com.example.demo.service.IOutilService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/outils")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OutilController {

    private final IOutilService outilService;

    @GetMapping
    public ResponseEntity<List<Outil>> getAllOutils() {
        return ResponseEntity.ok(outilService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Outil> getOutilById(@PathVariable Long id) {
        return ResponseEntity.ok(outilService.findOutil(id));
    }

    @GetMapping("/source/{source}")
    public ResponseEntity<List<Outil>> getOutilsBySource(@PathVariable String source) {
        return ResponseEntity.ok(outilService.findBySource(source));
    }

    @PostMapping
    public ResponseEntity<Outil> addOutil(@RequestBody Outil outil) {
        return new ResponseEntity<>(outilService.addOutil(outil), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Outil> updateOutil(@PathVariable Long id, @RequestBody Outil outil) {
        outil.setId(id);
        return ResponseEntity.ok(outilService.updateOutil(outil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutil(@PathVariable Long id) {
        outilService.deleteOutil(id);
        return ResponseEntity.noContent().build();
    }
}
