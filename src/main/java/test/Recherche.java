package test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recherche {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Selectionner le nom du fichier pour la recherche");
		Scanner scanner = new Scanner(System.in);
		String nomRepString = scanner.next();
		File file = new File("C:\\Users\\afpa\\Documents\\" + nomRepString);
		System.out.println(file.getName());
		InputStream inputStream = new FileInputStream(file.getAbsolutePath());
		Reader reader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;

		System.out.println("Quel mot souhaitez-vous recherchez?");
		String searchString = scanner.next().toLowerCase();
		int repere = 0;
		int first = 0;
		List<Character> tab = new ArrayList<>();
		List<String> listeStrings = new ArrayList<>();
		int compteur = 0;
		int numeroLigne = 0;
		String miniString = " ";
		try

		{
			while ((str = bufferedReader.readLine()) != null) {
				miniString = str.toLowerCase();
				listeStrings.add(miniString);
			}

			for (int u = 0; u < listeStrings.size(); u++) {
				++numeroLigne;
				compteur = 0;
				tab.clear();
				repere = 0;
				while (compteur < listeStrings.get(u).length()) {
					for (int j = 0; j < searchString.length(); j++) {
						for (int i = repere; i < listeStrings.get(u).length(); i++) {
							compteur++;
							if (searchString.charAt(j) == listeStrings.get(u).charAt(i)) {
								tab.add(listeStrings.get(u).charAt(i));
								repere = ++i;
								if (j == 0) {
									first = i;
								}
								break;
							}
							tab.clear();
						}
					}
					if (tab.size() == searchString.length()) {
						System.out.println(file.getName() + "(" + numeroLigne + "," + first + ")" + " : "
								+ listeStrings.get((numeroLigne - 1)));
						System.out.println("Le mot " + searchString + " est présent à la ligne " + numeroLigne
								+ " et à l'indice " + first);
						if (repere <= listeStrings.get(u).length()) {
							tab.clear();
						}
					}
				}
			}

		} catch (

		IOException e) {

			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
