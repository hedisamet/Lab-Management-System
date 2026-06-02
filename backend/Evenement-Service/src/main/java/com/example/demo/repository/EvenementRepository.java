package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Evenement;


public interface EvenementRepository extends JpaRepository<Evenement, Long>{

	
	List<Evenement> findByDate(Date date);
	List<Evenement> findByLieu(String lieu);
	Evenement findByTitre(String titre);
}
