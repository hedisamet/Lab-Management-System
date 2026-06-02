package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Outil;
import com.example.demo.repository.OutilRepository;

import lombok.AllArgsConstructor;

import java.util.Date;

@SpringBootApplication
@AllArgsConstructor
public class OutilServiceApplication implements CommandLineRunner {
    private final OutilRepository outilRepository;

    public static void main(String[] args) {
        SpringApplication.run(OutilServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Ajouter deux outils dans la base de données
        Outil outil1 = new Outil(new Date(), "Source 1");
        Outil outil2 = new Outil(new Date(), "Source 2");

        outilRepository.save(outil1);
        outilRepository.save(outil2);

        System.out.println("Outils ajoutés avec succès!");
    }
}
