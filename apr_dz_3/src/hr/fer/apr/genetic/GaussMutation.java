package hr.fer.apr.genetic;

import hr.fer.apr.opt.MatrixSolution;

import java.util.Random;

/**
 * Klasa koja implementira mutaciju po Gaussovoj razdiobi
 * @author Luka Sterbic
 * @version 0.1
 */
public class GaussMutation implements IMutation<MatrixSolution> {
	
	private double probability;
	private double sigma;
	private Random rand;
	
	/**
	 * Konstruktor za GaussMutation
	 * @param probability vjerojatnost mutacije jedne varijable
	 * @param gamma standardna devijacija
	 */
	public GaussMutation(double probability, double sigma) {
		if(sigma <= 0.0 || probability < 0.0 || probability > 1.0) {
			throw new IllegalArgumentException("Ilegalni parametri!");
		}
		
		this.probability = probability;
		this.sigma = sigma;
		this.rand = new Random();
	}

	@Override
	public void mutate(MatrixSolution chromosome) {
		for(int i = 0; i < chromosome.matrix.getRows(); i++) {
			if(rand.nextDouble() < probability) {
				chromosome.matrix.setAdd(i, 0, rand.nextGaussian() * sigma);
			}
		}
	}

}
