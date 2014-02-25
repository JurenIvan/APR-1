package hr.fer.apr.integration;

import java.util.Arrays;

import hr.fer.apr.algebra.Matrix;

/**
 * Implementacija trapeznog postupka numericke integracije
 * @author Luka Sterbic
 * @version 0.1
 */
public class TrapezoidalIntegrator implements INumericalIntegrator {

	private int verboseStep;

	/**
	 * Konstruktor za TrapezoidalIntegrator
	 * @param verboseStep broj koraka izmedju dva ispisa varijabli stanja
	 */
	public TrapezoidalIntegrator(int verboseStep) {
		this.verboseStep = verboseStep;
	}

	@Override
	public Matrix integrate(Matrix A, Matrix B, Matrix xInit, double T,
			double interval) {
		int steps = (int) Math.floor(interval / T);
		int vars = xInit.getRows();
		
		// transformacija u eksplicitni oblik
		Matrix AT2 = A.multiply(T / 2.0);
		Matrix U = new Matrix(vars);
		
		Matrix UsAT2inv = U.subtract(AT2).inverse();
		if(UsAT2inv == null) {
			return null;
		}
		
		Matrix R = UsAT2inv.multiply(U.add(AT2));
		Matrix S = UsAT2inv.multiply(T / 2.0).multiply(B);
		
		Matrix Xk = xInit.copy();

		Matrix values = new Matrix(vars, steps + 1);
		values.setColumn(xInit, 0);

		for (int step = 1; step <= steps; step++) {
			if(step % verboseStep == 0) {
				System.out.println(Arrays.toString(Xk.columnAsArray(0)));
			}
			
			Xk = R.multiply(Xk).add(S);
			values.setColumn(Xk, step);
		}

		return values;
	}

}
