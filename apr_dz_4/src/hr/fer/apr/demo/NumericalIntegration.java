package hr.fer.apr.demo;

import hr.fer.apr.algebra.Matrix;
import hr.fer.apr.integration.INumericalIntegrator;
import hr.fer.apr.integration.RungeKuttaIntegration;
import hr.fer.apr.integration.TrapezoidalIntegrator;

/**
 * Progam koji rjesava modificirani problem Max-Ones.
 * Program prima 4 argumenta iz komandne linije:
 * 1. staza do matrice A
 * 2. staza do matrice B
 * 3. staza do matrice X(t = 0)
 * 4. korak integracije T
 * 5. vremenski interval integracije
 * 6. identifikator integrator [Trapez, RK]
 * @author Luka Sterbic
 * @version 0.1
 */
public class NumericalIntegration {

	private static final String valuesPath = "values.txt";
	
	private static final int verboseStep = 10;

	/**
	 * Main funkcija programa
	 * @param args argumenti komandne linije
	 */
	public static void main(String[] args) {
		if(args.length != 6) {
			exitWithMsg("Krivi broj argumenata!");
		}
		
		Matrix A = Matrix.load(args[0]);

		Matrix B;
		if(args[1].equals("null") && A != null) {
			B = new Matrix(A.getRows(), 1);
		} else {
			B = Matrix.load(args[1]);
		}
		
		Matrix xInit = Matrix.load(args[2]);
		
		if(A == null || B == null || xInit == null) {
			exitWithMsg("Doslo je do greske prilikom ucitavanja matrica sustava!");
		}
		
		double T = 0.0;
		double interval = 0.0;
		
		try {
			T = Double.parseDouble(args[3]);
			interval = Double.parseDouble(args[4]);
		} catch(NumberFormatException ex) {
			exitWithMsg("Dogodila se greska prilikom parsiranja parametara!");
		}
		
		INumericalIntegrator integrator = null;
		
		switch(args[5]) {
		case "Trapez":
			integrator = new TrapezoidalIntegrator(verboseStep);
			break;
			
		case "RK":
			integrator = new RungeKuttaIntegration(verboseStep);
			break;

		default:
			exitWithMsg("Nepoznati postupak integracije!");
		}
		
		Matrix values = integrator.integrate(A, B, xInit, T, interval);
		values.save(valuesPath);
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
