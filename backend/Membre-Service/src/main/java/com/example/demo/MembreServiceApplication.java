package com.example.demo;

import java.util.Date;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.repository.EnseignantChercheurRepository;
import com.example.demo.repository.EtudiantRepository;
import com.example.demo.repository.MembreRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class MembreServiceApplication implements CommandLineRunner{
	MembreRepository membreRepository;
	public static void main(String[] args) {
		SpringApplication.run(MembreServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
	    byte[] placeholderPhoto = new byte[0]; // Placeholder photo

	    Etudiant etd1 = Etudiant.builder()
	            .cin("123456")
	            .dateInscription(new Date())
	            .dateNaissance(new Date())
	            .diplome("mastère")
	            .encadrant(null)
	            .nom("abid")
	            .prenom("youssef")
	            .photo(placeholderPhoto) // Initialize photo
	            .cv("default_cv")        // Initialize other @NonNull fields
	            .email("youssef.abid@example.com")
	            .password("password123")
	            .sujet("blockhain")
	            .build();
	    membreRepository.save(etd1);

	    Etudiant etd2 = Etudiant.builder()
	            .cin("165649")
	            .dateInscription(new Date())
	            .dateNaissance(new Date())
	            .diplome("ingénieurie")
	            .encadrant(null)
	            .nom("samet")
	            .prenom("hedi")
	            .photo(placeholderPhoto) // Initialize photo
	            .cv("default_cv")
	            .email("hedi.samet@example.com")
	            .password("password123")
	            .sujet("ai")
	            .build();
	    membreRepository.save(etd2);

	    EnseignantChercheur ens1 = EnseignantChercheur.builder()
	            .cin("165649")
	            .grade("professeur")
	            .dateNaissance(new Date())
	            .nom("samet")
	            .prenom("hedi")
	            .etablissement("ENIS")
	            .photo(placeholderPhoto) // Initialize photo
	            .cv("default_cv")
	            .email("hedi.samet@example.com")
	            .password("password123")
	            .build();
	    membreRepository.save(ens1);

	    membreRepository.findAll().forEach(mbr -> {
	        System.out.println(mbr.getNom() + " " + mbr.getPrenom());
	    });

	    // Example member modification logic
	    Long memberId = 1L;
	    Optional<Membre> membreOpt = membreRepository.findById(memberId);
	    if (membreOpt.isPresent()) {
	        Membre membre = membreOpt.get();
	        membre.setNom("Hssan");
	        membre.setPrenom("Mzid");
	        membreRepository.save(membre);
	        System.out.println("Updated Member: " + membre.getNom() + " " + membre.getPrenom());
	    } else {
	        System.out.println("Member not found with ID: " + memberId);
	    }
	}

}
