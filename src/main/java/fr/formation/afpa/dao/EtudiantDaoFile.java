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

	public List<Etudiant> getAll() {
		try {
			File fichier = new File("best/obj2.txt");
			if(!fichier.exists())
				fichier.createNewFile();
			
			InputStream is = new FileInputStream(fichier);

			ObjectInputStream fis = new ObjectInputStream(is);

			List<Etudiant> listEtudiant = new ArrayList<Etudiant>();

			listEtudiant = (List<Etudiant>) fis.readObject();
			fis.close();
			is.close();

			return listEtudiant;

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
		return null;

	}

	public void add(Etudiant student) {
		try {
			ArrayList<Etudiant> student1  = (ArrayList<Etudiant>) getAll();
			
			if(student1 != null)
				student1.add(student);
			else
				student1 = new ArrayList<Etudiant>();
			
			
			OutputStream os = new FileOutputStream("best/obj2.txt");
			ObjectOutputStream oos = new ObjectOutputStream(os);		


			oos.writeObject(student1);

			oos.close();
			os.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Etudiant update(Etudiant e) {
		return null;
	}

}
