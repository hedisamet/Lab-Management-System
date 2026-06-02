package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Evenement;
import com.example.demo.repository.EvenementRepository;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class EvenementServiceApplication implements CommandLineRunner {

	@Autowired
	private EvenementRepository evenementRepository;

	public static void main(String[] args) {
		SpringApplication.run(EvenementServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// Creating sample data
		Evenement evt1 = new Evenement("Conference", sdf.parse("2024-12-10"), "Sfax");
		Evenement evt2 = new Evenement("Workshop", sdf.parse("2024-11-25"), "Tunis");
		Evenement evt3 = new Evenement("Seminar", sdf.parse("2025-01-15"), "Monastir");

		// Saving data to the database
		evenementRepository.save(evt1);
		evenementRepository.save(evt2);
		evenementRepository.save(evt3);

		// Printing confirmation
		System.out.println("Sample events have been added to the database.");
	}
}
