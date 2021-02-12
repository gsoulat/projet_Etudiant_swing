package fr.formation.afpa.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.formation.afpa.dao.EtudiantDaoFile;
import fr.formation.afpa.dao.IEtudiantDao;
import fr.formation.afpa.model.Etudiant;

public class EtudiantService implements IEtudiantService {

	
	
	private IEtudiantDao dao = new EtudiantDaoFile();

	public List<Etudiant> listEtudiant() {
		return dao.getAll();
	}

	public void ajouterEtudiant(Etudiant e) {
		dao.add(e);
	}

	public Etudiant modifierEtudiant(List<Etudiant> e) {
		return dao.update(e);
	}

	public Etudiant trouverEtudiant(int id) throws FileNotFoundException, IOException {
		return dao.findEtudiant(id);
	}
	
}
