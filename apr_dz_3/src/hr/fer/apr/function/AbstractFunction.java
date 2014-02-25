package hr.fer.apr.function;

import hr.fer.apr.algebra.Matrix;

/**
 * Klasa koja modelira apstraktnu funkciju.
 * Sve metode iz sucelja IFunction koje koriste
 * tocku kao argument imaju provjeru dimenzija.
 * @author Luka Sterbic
 * @version 0.2
 */
public abstract class AbstractFunction implements IFunction {
	
	@Override
	public final double value(Matrix point) {
		checkDimensions(point);
		return valueAt(point);
	}
	
	/**
	 * Vrati vrijednost funkcije u zadanoj tocki
	 * @param point tocka
	 * @return vrijednost funkcije u tocki
	 */
	public abstract double valueAt(Matrix point);

	/**
	 * Provjeri da li zadana tocka i funkcija imaju istu dimenziju
	 * @param point stupcasta matrica
	 */
	private void checkDimensions(Matrix point) {
		if(!point.isVector(dimensions())) {
			throw new IllegalArgumentException("Dimenzije tocke i funkcije se ne podudaraju!");
		}
	}

}
