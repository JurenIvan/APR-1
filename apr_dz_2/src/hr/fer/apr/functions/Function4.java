package hr.fer.apr.functions;

import hr.fer.apr.algebra.Matrix;

/**
 * Implementacija funkcije f4(x, y) = |(x - y) * (x + y)| + (x^2 + y^2)^0.5
 * @author Luka Sterbic
 * @version 0.2
 */
public class Function4 extends AbstractFunction {

	@Override
	public int dimensions() {
		return 2;
	}

	@Override
	public double valueAt(Matrix point) {
		double x = point.get(0, 0);
		double y = point.get(1, 0);
		return Math.abs((x - y) * (x + y)) + Math.sqrt(x * x + y * y);
	}
	
	@Override
	public String toString() {
		return "f4(x, y) = |(x - y) * (x + y)| + (x^2 + y^2)^0.5";
	}

}
