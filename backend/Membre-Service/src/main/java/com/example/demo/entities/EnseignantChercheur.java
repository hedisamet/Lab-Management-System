package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ens")
public class EnseignantChercheur extends Membre implements Serializable {
	private static final long serialVersionUID = 1L;

	private String grade;
	private String etablissement;

	@OneToMany(mappedBy = "encadrant")
	private List<Etudiant> etudiantsEncadres;
	@Builder
	public EnseignantChercheur(Long id, @NonNull String cin, @NonNull String nom, @NonNull String prenom,
			@NonNull Date dateNaissance, @NonNull byte[] photo, @NonNull String cv, @NonNull String email,
			@NonNull String password, String grade, String etablissement, List<Etudiant> etudiantsEncadres) {
		super(id, cin, nom, prenom, dateNaissance, photo, cv, email, password);
		this.grade = grade;
		this.etablissement = etablissement;
		this.etudiantsEncadres = etudiantsEncadres;
	}
	
	

}
