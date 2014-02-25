package hr.fer.apr.integration;

import hr.fer.apr.algebra.Matrix;

/**
 * Sucelje koje predstavlja numericki integrator
 * @author Luka Sterbic
 * @version 0.1
 */
public interface INumericalIntegrator {
	
	/**
	 * Metoda koja pokrece postupak integracije
	 * @param A matrica A
	 * @param B matrica B
	 * @param xInit inicijalne vrijednosti varijabli stanja
	 * @param T korak integracije
	 * @param interval interval za koji se provodi postupak
	 * @return matrica koja sadrzi vrijednosti varijabli stanja tijekom
	 * postupka, retci predstavljaju varijable, a stupci vremenske trenutke 
	 */
	public Matrix integrate(Matrix A, Matrix B, Matrix xInit, double T, double interval);

}
