package hr.fer.apr.function;

import hr.fer.apr.algebra.Matrix;

/**
 * Implementacija funkcije f7
 * @author Luka Sterbic
 * @version 0.1
 */
public class Function7 extends AbstractFunction {

	private int dimensions;
	
	/**
	 * Konstruktor za Function7
	 * @param dimensions broj varijabli
	 */
	public Function7(int dimensions) {
		this.dimensions = dimensions;
	}
	
	@Override
	public int dimensions() {
		return dimensions;
	}

	@Override
	public double valueAt(Matrix point) {
		double sumX = point.transpose().multiply(point).get(0, 0);
		double result = Math.pow(sumX, 0.25);
		double sin = Math.sin(50 * Math.pow(sumX, 0.1));
		return result * (Math.pow(sin, 2) + 1.0);
	}

}
