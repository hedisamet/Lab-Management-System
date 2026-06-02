package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Membre;
import com.example.demo.service.IMemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/membres")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MembreController {

    private final IMemberService memberService;

    @GetMapping
    public ResponseEntity<List<Membre>> getAllMembres() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membre> getMembreById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findMember(id));
    }

    @GetMapping("/cin/{cin}")
    public ResponseEntity<Membre> getMbreByCin(@PathVariable String cin) {
        return ResponseEntity.ok(memberService.findByCin(cin));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Membre> getMembreByEmail(@PathVariable String email) {
        return ResponseEntity.ok(memberService.findByEmail(email));
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<List<Membre>> getMembresByNom(@PathVariable String nom) {
        return ResponseEntity.ok(memberService.findByNom(nom));
    }

    @PostMapping
    public ResponseEntity<Membre> addMembre(@RequestBody Membre membre) {
        return new ResponseEntity<>(memberService.addMember(membre), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membre> updateMembre(@PathVariable Long id, @RequestBody Membre membre) {
        membre.setId(id);
        return ResponseEntity.ok(memberService.updateMember(membre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembre(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
