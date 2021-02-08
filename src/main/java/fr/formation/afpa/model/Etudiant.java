package fr.formation.afpa.model;

import java.util.List;

public class Etudiant {
	private String nom;
	private String prenom;
	private String dateNaissance;
	List<Integer> notes;
//	Static id;
//	
//	Static {
//		id++;
//	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public List<Integer> getNotes() {
		return notes;
	}

	public void setNotes(List<Integer> notes) {
		this.notes = notes;
	}

	public Etudiant(String nom, String prenom, String dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", notes=" + notes
				+ "]";
	}

	public Etudiant() {
		// TODO Auto-generated constructor stub
	}
}
