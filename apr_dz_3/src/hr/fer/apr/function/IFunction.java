package hr.fer.apr.function;

import hr.fer.apr.algebra.Matrix;

/**
 * Sucelje koje modelira skalarnu funkciju nad n-dimenzijskim vektorom
 * @author Luka Sterbic
 * @version 0.2
 */
public interface IFunction {
	
	/**
	 * Vrati broj varijabli nad kojima je funkcija definirana
	 * @return broj varijabli
	 */
	public int dimensions();
	
	/**
	 * Vrati vrijednost funkcije u zadanoj tocki
	 * @param point stupcasta matrica
	 * @return vrijednost funkcije u tocki
	 */
	public double value(Matrix point);

}
