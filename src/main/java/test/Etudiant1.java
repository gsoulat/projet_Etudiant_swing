package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Etudiant1 implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	String nomString;
	String prenomString;
	String motDePasseString;
	List<Integer> notes;

	public Etudiant1(String nomString, String prenomString, String motDePasseString, List<Integer> notes) {
		super();
		this.nomString = nomString;
		this.prenomString = prenomString;
		this.motDePasseString = motDePasseString;
		this.notes = new ArrayList<>();
	}
	public Etudiant1() {
		
	}

	public Etudiant1(String nomString, String prenomString, String motDePasseString) {
		super();
		this.nomString = nomString;
		this.prenomString = prenomString;
		this.motDePasseString = motDePasseString;
		this.notes = new ArrayList<Integer>();
	}

	@Override
	public String toString() {
		return "Etudiant [nomString=" + nomString + ", prenomString=" + prenomString + "]";
	}

	public Double getMoyenne() {
		return (double) (notes.stream().mapToInt(Integer::intValue).sum() / notes.stream().count());
	}

	public String getNomString() {
		return nomString;
	}

	public void setNomString(String nomString) {
		this.nomString = nomString;
	}

	public String getPrenomString() {
		return prenomString;
	}

	public void setPrenomString(String prenomString) {
		this.prenomString = prenomString;
	}

	public String getMotDePasseString() {
		return motDePasseString;
	}

	public void setMotDePasseString(String motDePasseString) {
		this.motDePasseString = motDePasseString;
	}

	public List<Integer> getNotes() {
		return notes;
	}

	public void setNotes(List<Integer> notes) {
		this.notes = notes;
	}

}
