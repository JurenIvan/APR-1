package hr.fer.apr.algebra;

/**
 * Klasa s demo programom za demonstraciju rada klase Matrix
 * @author Luka Sterbic
 * @version 0.1
 */
public class Demo {
	
	/**
	 * Main metoda programa
	 * @param args argumenti komandne linije, nisu potrebni
	 */
	public static void main(String[] args) {		
		Matrix A = Matrix.load("A.txt");
		
		if(A == null) {
			exitWithMsg("Greska prilikom citanja matrice A.");
		}
		
		Matrix b = Matrix.load("b.txt");
		
		if(b == null) {
			exitWithMsg("Greska prilikom citanja vektora b.");
		}
		
		Matrix A2 = Matrix.load("A2.txt");
		
		if(A2 == null) {
			exitWithMsg("Greska prilikom citanja matrice A2.");
		}
		
		System.out.println("##########");
		SystemSolver.solve(A, b, true);
		System.out.println("##########");
		SystemSolver.solve(A2, b, true);
		System.out.println("##########");
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
