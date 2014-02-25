package hr.fer.apr.functions;

import hr.fer.apr.algebra.Matrix;

/**
 * Funkcija ogranicenja f(x1, x2) = x1 - x2
 * @author Luka Sterbic
 * @version 0.1
 */
public class ConstrintFuntion1 implements IFunction {

	@Override
	public int dimensions() {
		return 2;
	}

	@Override
	public double value(Matrix point) {
		return point.get(0, 0) - point.get(1, 0);
	}
	
}
