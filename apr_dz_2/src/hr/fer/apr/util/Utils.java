package hr.fer.apr.util;

/**
 * Klasa koja sadrzi utility staticke metode
 * @author Luka Sterbic
 * @version 0.1
 */
public class Utils {
	
	/**
	 * Konvertiraj zadani string u polje doublova
	 * @param string brojevi odvojeni zarezom i whitespaceom
	 * @return polje realnih brojeva
	 */
	public static double[] stringToDoubleArray(String string) {
		String[] parts = string.split(",\\s+");
		double[] array = new double[parts.length];
		
		for(int i = 0; i < parts.length; i++) {
			array[i] = Double.parseDouble(parts[i]);
		}
		
		return array;
	}
	
	/**
	 * Ispisi zadanu poruku na standardni izlaz i izadji iz programa
	 * @param message poruka greske
	 */
	public static void exitWithMsg(String message) {
		System.out.println(message);
		System.exit(-1);
	}

}
