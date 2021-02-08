package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Test {

	public static void main(String[] args) {

		int reponse = 100;
		File file = new File("test/etudiants.txt");
		Map<String, Etudiant1> listeMapEtudiantMap = new HashMap<>();
		Etudiant1 etudiantTrouve = new Etudiant1();
		Etudiant1 etudiant = new Etudiant1();

		while (reponse != 0) {
			int reponse2 = 100;
			int reponse3 = 100;
			System.out.println("Menu principal");
			System.out.println("1 - Créer un etudiant");
			System.out.println("2 - Afficher un etudiant");
			System.out.println("Quitter");

			System.out.println("Effectuer une selection ");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Reponse :");
			reponse = scanner.nextInt();
			scanner.nextLine();
			while (reponse2 != 0 && reponse != 0 && reponse3 != 0) {
				switch (reponse) {
				case 1:
					System.out.println("1 - Saisir ses coordonnées ");
					System.out.println("2 - Saisir ses notes ");
					System.out.println("3 - Enregister toutes informations ");
					System.out.println("0 - Retour ");
					reponse2 = scanner.nextInt();
					scanner.nextLine();

					switch (reponse2) {
					case 1:

						System.out.println("Merci de saisir son nom :");
						System.out.println("Nom :");
						String nom = scanner.next();
						System.out.println("Merci de saisir son prenom :");
						System.out.println("Prenom :");
						String prenom = scanner.next();
						System.out.println("Merci de saisir un mot de passe :");
						System.out.println("Mot de passe:");
						String mdp = scanner.next();

						etudiant = new Etudiant1(nom, prenom, mdp);
						listeMapEtudiantMap.put(nom, etudiant);

						break;
					case 2:
						System.out.println("Merci de saisir le nom de l'etudiant:");
						System.out.println("Nom :");
						String nomString = scanner.next();

						if (listeMapEtudiantMap.get(nomString) == null) {
							System.out.println("L'etudiant n'existe pas");
							break;
						} else {
							etudiantTrouve = listeMapEtudiantMap.get(nomString);
						}
						System.out.println("Combien de note à saisir");
						Integer nombreNoteInteger = scanner.nextInt();
						scanner.nextLine();
						int incr = 0;
						while (incr < nombreNoteInteger) {
							System.out.println("Saisir note n " + (incr + 1));
							System.out.println("Reponse : ");
							Integer noteInteger = scanner.nextInt();
							scanner.nextLine();

							etudiantTrouve.getNotes().add(noteInteger);
							incr++;

						}

						break;
					case 3:

						if (file.length() == 0) {

							enregistreUneListeEtudiant(file, listeMapEtudiantMap);

						} else {

							Map<String, Etudiant1> etudiants = recupereListEtudiantFichier(file);

							etudiants.put(etudiantTrouve.nomString, etudiantTrouve);

							enregistreUneListeEtudiant(file, etudiants);

						}

						break;

					default:
						break;
					}

					break;
				case 2:

					System.out.println("1 - Afficher ses informations ");
					System.out.println("2 - Afficher ses notes ");
					System.out.println("0 - Retour ");
					reponse3 = scanner.nextInt();
					scanner.nextLine();

					switch (reponse3) {
					case 1:

						System.out.println("Merci de saisir le nom de l'etudiant:");
						System.out.println("Nom :");
						String nomString = scanner.next();

						Map<String, Etudiant1> etudiants = recupereListEtudiantFichier(file);

						etudiantTrouve = etudiants.get(nomString);

						System.out.println(etudiantTrouve);
						System.out.println(" ");

						break;
					case 2:
						System.out.println("Merci de saisir le nom de l'etudiant:");
						System.out.println("Nom :");
						String nomString2 = scanner.next();

						Map<String, Etudiant1> etudiants2 = recupereListEtudiantFichier(file);

						Etudiant1 etudiantTrouve2 = etudiants2.get(nomString2);

						System.out.println("Ci-dessous la liste de ses notes :");

						int incr = 0;
						for (Integer note : etudiantTrouve2.getNotes()) {
							incr++;
							System.out.println(" - Note " + incr + " : " + note);
						}
						System.out.println(" ");
						break;

					default:
						break;
					}
				}

			}
		}

	}

	public static Map<String, Etudiant1> recupereListEtudiantFichier(File file) {

		try {
			FileInputStream os = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(os);
			HashMap<String, Etudiant1> etudiants = (HashMap<String, Etudiant1>) ois.readObject();
			return etudiants;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void enregistreUneListeEtudiant(File file, Map<String, Etudiant1> listeMap) {

		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(listeMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
