package org.alberto.cinema.businesslogic.utilities;

public class Utilities {
	
	public static String rendiInizMaiuscERestoMin(String s) {
		String inizMaiusc = s.substring(0, 1).toUpperCase();
		String restoDellaStringa = s.substring(1).toLowerCase();
		String stringaCompleta = inizMaiusc + restoDellaStringa;
		return stringaCompleta;
	}

}
