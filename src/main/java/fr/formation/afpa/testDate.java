package fr.formation.afpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testDate {

	public static boolean check(String date) {
		// Définir le format date préféré
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		try {
			Date d = format.parse(date);
			System.out.println(date + " est une date valide");
		}
		// Date invalide
		catch (ParseException e) {
			System.out.println(date + " est une date invalide");
			return false;
		}
		// Renvoie true si la date est valide
		return true;
	}
}