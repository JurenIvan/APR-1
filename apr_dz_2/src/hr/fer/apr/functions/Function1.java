package hr.fer.apr.functions;

import hr.fer.apr.algebra.Matrix;

/**
 * Implementacija funkcije f1(x, y) = 10 * (x^2 - y)^2 + (1 - x)^2
 * @author Luka Sterbic
 * @version 0.2
 */
public class Function1 extends AbstractFunction {

	@Override
	public int dimensions() {
		return 2;
	}

	@Override
	public double valueAt(Matrix point) {
		double x = point.get(0, 0);
		double y = point.get(1, 0);
		return 10 * Math.pow(x * x - y, 2) + Math.pow(1 - x, 2);
	}
	
	@Override
	public String toString() {
		return "f1(x, y) = 10 * (x^2 - y)^2 + (1 - x)^2";
	}

}
