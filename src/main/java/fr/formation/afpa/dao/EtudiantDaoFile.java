package fr.formation.afpa.dao;

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

public class EtudiantDaoFile implements IEtudiantDao{

	
	public List<Etudiant> getAll() {
		try {
			InputStream is = new FileInputStream("best/obj.txt");
			ObjectInputStream fis = new ObjectInputStream(is);
			Etudiant etudiant = (Etudiant) fis.readObject();
			List<Etudiant> ListEtudiant = new ArrayList<Etudiant>();
			
			ListEtudiant.add(etudiant);
			
			System.out.println(etudiant);

			fis.close();
			is.close();
			
			return ListEtudiant;
			
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
			OutputStream os = new FileOutputStream("best/obj.txt");
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(student);
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
