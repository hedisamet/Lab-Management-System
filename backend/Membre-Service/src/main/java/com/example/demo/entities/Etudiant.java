package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("etd")
public class Etudiant extends Membre implements Serializable{
	private static final long serialVersionUID =1L;
	@Temporal(TemporalType.DATE)
    private Date dateInscription;
    private String diplome;
    private String sujet;
    @ManyToOne
    private EnseignantChercheur encadrant;
    @Builder
	public Etudiant(Long id, @NonNull String cin, @NonNull String nom, @NonNull String prenom,
			@NonNull Date dateNaissance, @NonNull byte[] photo, @NonNull String cv, @NonNull String email,
			@NonNull String password, Date dateInscription, String diplome, String sujet,
			EnseignantChercheur encadrant) {
		super(id, cin, nom, prenom, dateNaissance, photo, cv, email, password);
		this.dateInscription = dateInscription;
		this.diplome = diplome;
		this.sujet = sujet;
		this.encadrant = encadrant;
	}
    
	
}
