package hr.fer.apr.genetic;

import hr.fer.apr.function.IFunction;
import hr.fer.apr.opt.IOptAlgorithm;
import hr.fer.apr.opt.MatrixSolution;

import java.util.Random;

/**
 * Klasa koja implementira steady state genetski
 * algoritam za kombinatoricku optimizaciju.
 * @author Luka Sterbic
 * @version 0.7
 */
public class SteadyStateGeneticAlgorithm implements IOptAlgorithm<MatrixSolution> {
	
	private IFunction function;
	private int populationSize;
	private ICrossover[] crossovers;
	private IMutation<MatrixSolution> mutation;
	private int maxIter;
	
	/**
	 * Konstruktor za SteadyStateGeneticAlgorithm
	 * @param function funkcija cilja
	 * @param populationSize velicina populacije
	 * @param crossover objekt koji vrsi krizanje
	 * @param mutation objekt koji vrsi mutaciju
	 * @param maxIter maksimalni broj iteracija
	 */
	public SteadyStateGeneticAlgorithm(IFunction function,
			int populationSize,	IMutation<MatrixSolution> mutation,
			int maxIter, ICrossover... crossovers) {
		this.function = function;
		this.populationSize = populationSize;
		this.crossovers = crossovers;
		this.mutation = mutation;
		this.maxIter = maxIter;
	}
	
	@Override
	public MatrixSolution run() {
		Random rand = new Random();
		MatrixSolution[] population = new MatrixSolution[populationSize];
		
		for(int i = 0; i < populationSize; i++) {
			population[i] = new MatrixSolution(function.dimensions(), rand);
			evaluate(population[i]);
		}
		
		for(int iter = 1; iter <= maxIter; iter++) {
			int i = rand.nextInt(populationSize);
			
			int j = rand.nextInt(populationSize);
			while(i == j) {
				j = rand.nextInt(populationSize);
			}
			
			int k = rand.nextInt(populationSize);
			while(i == k || j == k) {
				k = rand.nextInt(populationSize);
			}
			
			if(population[i].fitness > population[j].fitness) {
				int t = i;
				i = j;
				j = t;
			}
			
			if(population[i].fitness > population[k].fitness) {
				int t = i;
				i = k;
				k = t;
			}
			
			ICrossover crossover = crossovers[rand.nextInt(crossovers.length)];
			MatrixSolution child = crossover.cross(population[j], population[k]);
			
			mutation.mutate(child);
			
			evaluate(child);
			System.out.println(iter + ". iteracija:, vrijednost = " + function.value(child.matrix));
			
			population[i] = child;
		}
		
		MatrixSolution solution = population[0];
		
		for(int i = 1; i < populationSize; i++) {
			if(population[i].compareTo(solution) > 0) {
				solution = population[i];
			}
		}
		
		return solution;
	}

	/**
	 * Evaluiraj zadano rjesenje
	 * @param solution rjesenje
	 */
	private void evaluate(MatrixSolution solution) {
		solution.value = function.value(solution.matrix);
		solution.fitness = -solution.value;
	}

}
