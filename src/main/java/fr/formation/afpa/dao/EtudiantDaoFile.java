package fr.formation.afpa.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import fr.formation.afpa.model.Etudiant;

public class EtudiantDaoFile implements IEtudiantDao {
	String path = "best/ob3.txt";

	public List<Etudiant> getAll() {
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		File fichier = new File(path);
		if (!fichier.exists()) {
			try {
				fichier.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!(fichier.length() == 0)) {
			try {
				InputStream is = new FileInputStream(fichier);
				ObjectInputStream fis = new ObjectInputStream(is);
				listEtudiant = (List<Etudiant>) fis.readObject();
				fis.close();
				is.close();
			} catch (FileNotFoundException fnfe) {
				System.out.println("fichier non trouvé");
				fnfe.printStackTrace();
			} catch (ClassNotFoundException cnfe) {
				System.out.println("Fichier format mauvais :(");
				cnfe.printStackTrace();
			} catch (IOException ioe) {
				System.out.println("I/O Exception while reading file");
				ioe.printStackTrace();
			}
		}
		return listEtudiant;
	}

	public void add(Etudiant studentAdd) {
		List<Etudiant> listEtudiant = getAll();
		listEtudiant.add(studentAdd);
		try {
			OutputStream os = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(listEtudiant);
			oos.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Etudiant update(Etudiant student) {
		try {
			List<Etudiant> listEtudiant = getAll();

			for (int i = 0; i < listEtudiant.size(); i++) {
				int etudiant = listEtudiant.get(i).getIdEtudiant();
			}

			OutputStream os = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(listEtudiant);
			oos.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
