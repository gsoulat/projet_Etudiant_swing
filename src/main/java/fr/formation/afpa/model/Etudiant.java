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
	private String photo;
	
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
	
	public Etudiant(String nom, String prenom, String dateNaissance, String photo) {
		super();
		idEtudiant = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.photo = photo;
	}

	public Etudiant(int id,String nom, String prenom, String dateNaissance, String photo) {
		super();
		idEtudiant = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", photo=" + photo
				+ ", notes=" + notes + ", idEtudiant=" + idEtudiant + "]";
	}

	public int getIdEtudiant() {
		return idEtudiant;
	}

	public Etudiant() {
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
