package fr.formation.afpa.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.formation.afpa.model.Etudiant;

public interface IEtudiantDao {
	
	public List<Etudiant> getAll();
	
	public void add(Etudiant e);
		
	
	public Etudiant update(List<Etudiant> e);
	
	public Etudiant findEtudiant(int id) throws FileNotFoundException, IOException;

}
