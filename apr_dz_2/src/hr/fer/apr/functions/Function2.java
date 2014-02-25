 package hr.fer.apr.functions;

import hr.fer.apr.algebra.Matrix;

/**
 * Implementacija funkcije f2(x, y) = (x - 4)^2 + 4 * (y - 2)^2
 * @author Luka Sterbic
 * @version 0.2
 */
public class Function2 extends AbstractFunction {

	@Override
	public int dimensions() {
		return 2;
	}

	@Override
	public double valueAt(Matrix point) {
		double x = point.get(0, 0);
		double y = point.get(1, 0);
		return Math.pow(x - 4, 2) + 4 * Math.pow(y - 2, 2);
	}
	
	@Override
	public String toString() {
		return "f2(x, y) = (x - 4)^2 + 4 * (y - 2)^2";
	}

}
