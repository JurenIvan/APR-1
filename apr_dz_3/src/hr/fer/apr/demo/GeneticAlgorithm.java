package hr.fer.apr.demo;

import hr.fer.apr.algebra.Matrix;
import hr.fer.apr.function.Function3;
import hr.fer.apr.function.Function4;
import hr.fer.apr.function.Function6;
import hr.fer.apr.function.Function7;
import hr.fer.apr.function.IFunction;
import hr.fer.apr.genetic.ArithmeticCrossover;
import hr.fer.apr.genetic.BLXAlphaCrossover;
import hr.fer.apr.genetic.GaussMutation;
import hr.fer.apr.genetic.SteadyStateGeneticAlgorithm;
import hr.fer.apr.opt.IOptAlgorithm;
import hr.fer.apr.opt.MatrixSolution;

/**
 * Progam koji rjesava optimizacijski problem uporabom genetskog algoritma.
 * Program prima 4 argumenta iz komandne linije:
 * 1. identifikator funkcije [3, 4, 6, 7]
 * 2. velicina populacije
 * 3. vjerojatnost mutacije
 * 4. maksimalni broj iteracija
 * @author Luka Sterbic
 * @version 0.1
 */
public class GeneticAlgorithm {

	private static final double sigma = 0.1;
	private static final double[] function3params = new double[]{2, 5, -1, 3, -4};
	private static final int dimensions = 3;
	private static final double alpha = 0.25;

	/**
	 * Main funkcija programa
	 * @param args argumenti komandne linije
	 */
	public static void main(String[] args) {
		if(args.length != 4) {
			exitWithMsg("Krivi broj argumenata!");
		}
		
		int functionNumber = -1;
		int maxPopulationSize = -1;
		double probability = -1.0;
		int maxIterations = -1;
		
		try {
			functionNumber = Integer.parseInt(args[0]);
			maxPopulationSize = Integer.parseInt(args[1]);
			probability = Double.parseDouble(args[2]);
			maxIterations = Integer.parseInt(args[3]);
		} catch(NumberFormatException ignorable) {}

		if(maxPopulationSize < 10) {
			exitWithMsg("Maksimalna velicina populacije mora biti barem 10!");
		} else if(maxIterations < 1) {
			exitWithMsg("Maksimalni broj iteracija mora biti veci od nule!");
		}
		
		IFunction function = null;
		
		switch(functionNumber) {	
		case 3:
			function = new Function3(new Matrix(function3params));
			break;
			
		case 4:
			function = new Function4();
			break;
			
		case 6:
			function = new Function6(dimensions);
			break;
			
		case 7:
			function = new Function7(dimensions);
			break;

		default:
			exitWithMsg("Nepoznata funkcija!");;
		}
		
		IOptAlgorithm<MatrixSolution> steadyState = new SteadyStateGeneticAlgorithm(
				function,
				maxPopulationSize,
				new GaussMutation(probability, sigma),
				maxIterations,
				new BLXAlphaCrossover(alpha),
				new ArithmeticCrossover()
				);
		
		MatrixSolution solution = steadyState.run();
		
		System.out.println("Solution: " + solution);
		System.out.println("Value at solution: " + solution.value);
	}
	
	/**
	 * Ispisi zadanu poruku na standardni izlaz i izadji iz programa
	 * @param message poruka greske
	 */
	private static void exitWithMsg(String message) {
		System.out.println(message);
		System.exit(-1);
	}
	
}
