package fr.formation.afpa.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.formation.afpa.model.Etudiant;

public interface IEtudiantService {
	
	public List<Etudiant> listEtudiant();
	
	public void ajouterEtudiant(Etudiant student);
	
	public Etudiant modifierEtudiant(List<Etudiant> e);
	
	public Etudiant trouverEtudiant(int id) throws FileNotFoundException, IOException ;

}
