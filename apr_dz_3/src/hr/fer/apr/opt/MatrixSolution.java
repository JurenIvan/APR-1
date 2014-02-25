package hr.fer.apr.opt;

import java.util.Random;

import hr.fer.apr.algebra.Matrix;

/**
 * Klasa koja predstavlja rjesenje optimitacijskog
 * problema u obliku jednostupcane matrice.
 * @author Luka Sterbic
 * @version 0.1
 */
public class MatrixSolution extends SingleObjectiveSolution {
	
	private static final double DELTA = 100.0;
	
	public Matrix matrix;

	/**
	 * Konstruktor za MatrixSolution
	 * @param solution rjesenje u obliku matrice
	 */
	public MatrixSolution(Matrix solution) {
		this.matrix = solution;
	}
	
	/**
	 * Konstruktor za MatrixSolution, slucajna inicijalizacija
	 * @param dimensions broj varijabli
	 * @param rand Random objekt
	 */
	public MatrixSolution(int dimensions, Random rand) {
		double[] vector = new double[dimensions];
		
		for(int i = 0; i < dimensions; i++) {
			vector[i] = rand.nextDouble() * (rand.nextBoolean() ? DELTA : -DELTA);
		}
		
		this.matrix = new Matrix(vector);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		for(int i = 0; i < matrix.getRows(); i++) {
			sb.append(matrix.get(i, 0));
			
			if(i != matrix.getRows() - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}

}
