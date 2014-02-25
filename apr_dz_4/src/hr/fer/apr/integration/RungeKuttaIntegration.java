package hr.fer.apr.integration;

import java.util.Arrays;

import hr.fer.apr.algebra.Matrix;

/**
 * Implementacija Runge-Kutta postupka 4. reda
 * @author Luka Sterbic
 * @version 0.1
 */
public class RungeKuttaIntegration implements INumericalIntegrator {

	private int verboseStep;

	/**
	 * Konstruktor za RungeKuttaIntegration
	 * @param verboseStep broj koraka izmedju dva ispisa varijabli stanja
	 */
	public RungeKuttaIntegration(int verboseStep) {
		this.verboseStep = verboseStep;
	}

	@Override
	public Matrix integrate(Matrix A, Matrix B, Matrix xInit, double T,
			double interval) {
		int steps = (int) Math.floor(interval / T);
		int vars = xInit.getRows();
		
		Matrix Xk = xInit.copy();

		Matrix values = new Matrix(vars, steps + 1);
		values.setColumn(xInit, 0);

		for (int step = 1; step <= steps; step++) {
			if(step % verboseStep == 0) {
				System.out.println(Arrays.toString(Xk.columnAsArray(0)));
			}

			Matrix m1 = A.multiply(Xk).addToThis(B);
			Matrix m2 = A.multiply(Xk.add(m1.multiply(T / 2.0))).addToThis(B);
			Matrix m3 = A.multiply(Xk.add(m2.multiply(T / 2.0))).addToThis(B);
			Matrix m4 = A.multiply(Xk.add(m3.multiply(T))).addToThis(B);
			
			Matrix m = m1.addToThis(m2.multiply(2.0)).addToThis(m3.multiply(2.0)).addToThis(m4);
			Xk = Xk.add(m.multiplyToThis(T / 6.0));
			
			values.setColumn(Xk, step);
		}

		return values;
	}
	
}
