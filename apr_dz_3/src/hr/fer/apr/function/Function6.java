package hr.fer.apr.function;

import hr.fer.apr.algebra.Matrix;

/**
 * Implementacija funkcije f6
 * @author Luka Sterbic
 * @version 0.1
 */
public class Function6 extends AbstractFunction {

	private int dimensions;
	
	/**
	 * Konstruktor za Function6
	 * @param dimensions broj varijabli
	 */
	public Function6(int dimensions) {
		this.dimensions = dimensions;
	}
	
	@Override
	public int dimensions() {
		return dimensions;
	}

	@Override
	public double valueAt(Matrix point) {
		double sumX = point.transpose().multiply(point).get(0, 0);
		double numerator = Math.pow(Math.sin(Math.sqrt(sumX)), 2) - 0.5;
		double denominator = Math.pow(1 + 0.001 * sumX, 2);
		return 0.5 + numerator / denominator;
	}

}
