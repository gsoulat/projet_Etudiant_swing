package fr.formation.afpa.model;

import java.io.Serializable;
import java.util.List;

public class Etudiant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private String dateNaissance;
	
	List<Integer> notes;
	
	static int id;
	private int idEtudiant;
	
	static {
		id++;
	}
	
	
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
		idEtudiant = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", notes=" + notes
				+ "]";
	}

	public int getIdEtudiant() {
		return idEtudiant;
	}

	public Etudiant() {
	}
}
