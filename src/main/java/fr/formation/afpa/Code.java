package fr.formation.afpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.formation.afpa.model.Etudiant;


public class Code implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codage;
	private String pwd;
	private String login;
	private static String path = "./pwd.txt";
	
	public Code() {
		// TODO Auto-generated constructor stub
	}
	
	public Code(String codage) {
		this.codage = codage;

	}
	
	public static void ecrireCode(String string) {
		try {
			OutputStream os = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(string);
			oos.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String lireCode() {	
	File fichier = new File(path);
	if (!fichier.exists()) {
		try {
			fichier.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	if (!(fichier.length() == 0)) {
		try {
			InputStream is = new FileInputStream(fichier);
			ObjectInputStream fis = new ObjectInputStream(is);
			String mot = (String) fis.readObject();
			fis.close();
			is.close();
			return mot;
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
	return null;
}
	
	public static String encode(String code) {
		String morse = code;

		if (code.equalsIgnoreCase("a"))
			morse = "&*";
		if (code.equalsIgnoreCase("b"))
			morse = "*&&&";
		if (code.equalsIgnoreCase("c"))
			morse = "*&*&";
		if (code.equalsIgnoreCase("d"))
			morse = "*&&";
		if (code.equalsIgnoreCase("e"))
			morse = "&";
		if (code.equalsIgnoreCase("f"))
			morse = "&&*&";
		if (code.equalsIgnoreCase("g"))
			morse = "**&";
		if (code.equalsIgnoreCase("h"))
			morse = "&&&&";
		if (code.equalsIgnoreCase("i"))
			morse = "&&";
		if (code.equalsIgnoreCase("j"))
			morse = "&***";
		if (code.equalsIgnoreCase("k"))
			morse = "*&*";
		if (code.equalsIgnoreCase("l"))
			morse = "&*&&";
		if (code.equalsIgnoreCase("m"))
			morse = "**";
		if (code.equalsIgnoreCase("n"))
			morse = "*&";
		if (code.equalsIgnoreCase("o"))
			morse = "***";
		if (code.equalsIgnoreCase("p"))
			morse = "&**&";
		if (code.equalsIgnoreCase("q"))
			morse = "**&*";
		if (code.equalsIgnoreCase("r"))
			morse = "&*&";
		if (code.equalsIgnoreCase("s"))
			morse = "&&&";
		if (code.equalsIgnoreCase("t"))
			morse = "*";
		if (code.equalsIgnoreCase("u"))
			morse = "&&*";
		if (code.equalsIgnoreCase("v"))
			morse = "&&&*";
		if (code.equalsIgnoreCase("w"))
			morse = "&**";
		if (code.equalsIgnoreCase("x"))
			morse = "*&&*";
		if (code.equalsIgnoreCase("y"))
			morse = "*&**";
		if (code.equalsIgnoreCase("z"))
			morse = "**&&";
		if (code.equalsIgnoreCase("0"))
			morse = "*****";
		if (code.equalsIgnoreCase("1"))
			morse = "&****";
		if (code.equalsIgnoreCase("2"))
			morse = "&&***";
		if (code.equalsIgnoreCase("3"))
			morse = "...**";
		if (code.equalsIgnoreCase("4"))
			morse = "&&&&*";
		if (code.equalsIgnoreCase("5"))
			morse = "&&&&&";
		if (code.equalsIgnoreCase("6"))
			morse = "*&&&&";
		if (code.equalsIgnoreCase("7"))
			morse = "**&&&";
		if (code.equalsIgnoreCase("8"))
			morse = "***&&";
		if (code.equalsIgnoreCase("9"))
			morse = "****&";
		if (code.equalsIgnoreCase("."))
			morse = "&*&*";
		if (code.equalsIgnoreCase(","))
			morse = "**&&**";
		if (code.equalsIgnoreCase("?"))
			morse = "&&**&&";

		return morse;
	}

	public static String decode(String code) {
		String morse = code;

		if (code.equalsIgnoreCase("&*"))
			morse = "a";
		if (code.equalsIgnoreCase("*&&&"))
			morse = "b";
		if (code.equalsIgnoreCase("*&*&"))
			morse = "c";
		if (code.equalsIgnoreCase("*&&"))
			morse = "d";
		if (code.equalsIgnoreCase("&"))
			morse = "e";
		if (code.equalsIgnoreCase("&&*&"))
			morse = "f";
		if (code.equalsIgnoreCase("**&"))
			morse = "g";
		if (code.equalsIgnoreCase("&&&&"))
			morse = "h";
		if (code.equalsIgnoreCase("&&"))
			morse = "i";
		if (code.equalsIgnoreCase("&***"))
			morse = "j";
		if (code.equalsIgnoreCase("*&*"))
			morse = "k";
		if (code.equalsIgnoreCase("&*&&"))
			morse = "l";
		if (code.equalsIgnoreCase("**"))
			morse = "m";
		if (code.equalsIgnoreCase("*&"))
			morse = "n";
		if (code.equalsIgnoreCase("***"))
			morse = "o";
		if (code.equalsIgnoreCase("&**&"))
			morse = "p";
		if (code.equalsIgnoreCase("**&*"))
			morse = "q";
		if (code.equalsIgnoreCase("&*&"))
			morse = "r";
		if (code.equalsIgnoreCase("&&&"))
			morse = "s";
		if (code.equalsIgnoreCase("*"))
			morse = "t";
		if (code.equalsIgnoreCase("&&*"))
			morse = "u";
		if (code.equalsIgnoreCase("&&&*"))
			morse = "v";
		if (code.equalsIgnoreCase("&**"))
			morse = "w";
		if (code.equalsIgnoreCase("*&&*"))
			morse = "x";
		if (code.equalsIgnoreCase("*&**"))
			morse = "y";
		if (code.equalsIgnoreCase("**&&"))
			morse = "z";
		if (code.equalsIgnoreCase("*****"))
			morse = "0";
		if (code.equalsIgnoreCase("&****"))
			morse = "1";
		if (code.equalsIgnoreCase("&&***"))
			morse = "2";
		if (code.equalsIgnoreCase("&&&**"))
			morse = "3";
		if (code.equalsIgnoreCase("&&&&*"))
			morse = "4";
		if (code.equalsIgnoreCase("&&&&&"))
			morse = "5";
		if (code.equalsIgnoreCase("*&&&&"))
			morse = "6";
		if (code.equalsIgnoreCase("**&&&"))
			morse = "7";
		if (code.equalsIgnoreCase("***&&"))
			morse = "8";
		if (code.equalsIgnoreCase("****&"))
			morse = "9";
		if (code.equalsIgnoreCase("|"))
			morse = "";

		return morse;
	}

	public static String stringToMorse(String text) {

		String newText = "";
		String selectChar;
		String convertirChar;
		for (int i = 0; i < text.length(); i++) {
			selectChar = text.charAt(i) + "";
			convertirChar = encode(selectChar);
			if (convertirChar.equals(" "))
			{
				newText = newText + "| ";
			}
			else {
				newText = newText + convertirChar;
				if (!convertirChar.equals(" ")) {
					newText = newText + " ";
				}
			}
		}
		return newText;
	}

	public static String stringToFrancais( String text )
	{
	   String newFrancais = "";
	   String selectFrancais; 
	   String convertirFrancais; 
	   String[] morseChars = text.split(" ");
	   for (int i = 0; i < morseChars.length; i++)
	   {
	       selectFrancais = morseChars[i];
	       boolean Separateur = selectFrancais.endsWith("|");
	       if(Separateur) selectFrancais = selectFrancais.substring(0, selectFrancais.length() - 1);

	       convertirFrancais = decode(selectFrancais);

	       newFrancais = newFrancais + convertirFrancais;
	       if (Separateur) 
	       {
	           newFrancais = newFrancais + " ";
	       }
	   }

	   return newFrancais;
	}

}
