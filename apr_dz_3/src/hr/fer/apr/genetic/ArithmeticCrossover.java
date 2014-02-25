package hr.fer.apr.genetic;

import hr.fer.apr.algebra.Matrix;
import hr.fer.apr.opt.MatrixSolution;

/**
 * Implementacija aritmetickog krizanja
 * @author Luka Sterbic
 * @version 0.1
 */
public class ArithmeticCrossover implements ICrossover {

	@Override
	public MatrixSolution cross(MatrixSolution firstParent,
			MatrixSolution secondParent) {
		Matrix matrix = firstParent.matrix.add(secondParent.matrix);
		return new MatrixSolution(matrix.multiplyToThis(0.5));
	}

}
