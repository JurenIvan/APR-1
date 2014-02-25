package hr.fer.apr.genetic;

import hr.fer.apr.algebra.Matrix;
import hr.fer.apr.opt.MatrixSolution;

import java.util.Random;

/**
 * Klasa koja implementira BLX-Alpha krizanje
 * @author Luka Sterbic
 * @version 0.2
 */
public class BLXAlphaCrossover implements ICrossover {
	
	private double alpha;
	private Random rand;
	
	/**
	 * Konstruktor za BLXAlphaCross
	 * @param alpha parametar alpha
	 */
	public BLXAlphaCrossover(double alpha) {
		this.alpha = alpha;
		this.rand = new Random();
	}

	@Override
	public MatrixSolution cross(MatrixSolution firstParent, 
			MatrixSolution secondParent) {
		if(firstParent == null || secondParent == null) {
			throw new IllegalArgumentException("Roditelji ne smiju biti null!");
		}
		
		int dimensions = firstParent.matrix.getRows();
		
		if(dimensions != secondParent.matrix.getRows()) {
			throw new IllegalArgumentException("Dimenzije roditelja se ne podudaraju!");
		}
		
		double[] child = new double[dimensions];
		
		for(int j = 0; j < dimensions; j++) {
			double cMin = Math.min(firstParent.matrix.get(j, 0), secondParent.matrix.get(j, 0));
			double cMax = Math.max(firstParent.matrix.get(j, 0), secondParent.matrix.get(j, 0));
			double I = alpha * (cMax - cMin);
			
			cMin -= I;
			cMax += I;
			
			child[j] = cMin + rand.nextDouble() * (cMax - cMin);
		}
		
		return new MatrixSolution(new Matrix(child));
	}

}
