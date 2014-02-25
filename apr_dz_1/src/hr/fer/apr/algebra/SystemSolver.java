package hr.fer.apr.algebra;

/**
 * Klasa koja implementira staticku metodu za rjesavanje linearnog sustava
 * @author Luka Sterbic
 * @version 0.1
 */
public class SystemSolver {
	
	/**
	 * Metoda koja rjesava sustav jednadzbi oblika A * x = b
	 * @param A matrica A
	 * @param b vektor b
	 * @param verbose true za ispis medjukoraka, false inace
	 * @return vektor rjesenja
	 */
	public static Matrix solve(Matrix A, Matrix b, boolean verbose) {
		try {
			Matrix copyOfA = A.copy(); 
			
			if(verbose) {
				System.out.println("A:\n" + copyOfA.toString() + "\n");
				System.out.println("b:\n" + b.toString() + "\n");
			}
			
			Matrix P = copyOfA.decomposeLU(true);
			
			if(P == null) {
				System.out.println("Dekompozicija nije uspijela!");
				return null;
			}
			
			if(verbose) {
				System.out.println("L:\n" + copyOfA.getL().toString() + "\n");
				System.out.println("U:\n" + copyOfA.getU().toString() + "\n");
				System.out.println("P:\n" + P.toString() + "\n");
			}
			
			b = P.multiply(b);
			
			if(verbose) {
				System.out.println("b:\n" + b.toString() + "\n");
			}
			
			Matrix y = copyOfA.forwardSubstitution(b);
			
			if(verbose) {
				System.out.println("y:\n" + y.toString() + "\n");
			}
			
			Matrix x = copyOfA.backSubstitution(y);	
			
			if(verbose) {
				System.out.println("x:\n" + x.toString() + "\n");
			}
			
			return x;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
