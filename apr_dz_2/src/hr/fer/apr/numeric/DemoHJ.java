package hr.fer.apr.numeric;

import hr.fer.apr.algebra.Matrix;
import hr.fer.apr.functions.Function1;
import hr.fer.apr.functions.Function2;
import hr.fer.apr.functions.Function3;
import hr.fer.apr.functions.Function4;
import hr.fer.apr.functions.IFunction;
import hr.fer.apr.util.Utils;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Klasa s demo programom za testiranje postupka Hook-Jeeves
 * Program prima 2 argumenta iz komandne linije:
 * 1. broje funkcije koja se optimira [1, 2, 3, 4]
 * 2. staza do datoteke s konfiguracijom pocetnih uvjeta
 * @author Luka Sterbic
 * @version 0.4
 */
public class DemoHJ {
	
	/**
	 * Main metoda programa
	 * @param args argumenti komandne linije
	 */
	public static void main(String[] args) {
		if(args.length != 2) {
			Utils.exitWithMsg("Krivi broj argumenta!");
		}
		
		int functionNumber = -1;
		
		try {
			functionNumber = Integer.parseInt(args[0]);
		} catch(NumberFormatException ignorable) {}
		
		Matrix startingPoint = null;
		Matrix dx = null;
		Matrix epsilon = null;
		Matrix params = null;
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(args[1]), Charset.defaultCharset());
			
			startingPoint = new Matrix(Utils.stringToDoubleArray(lines.get(0)));
			dx = new Matrix(Utils.stringToDoubleArray(lines.get(1)));
			epsilon = new Matrix(Utils.stringToDoubleArray(lines.get(2)));
			
			if(functionNumber == 3) {
				params = new Matrix(Utils.stringToDoubleArray(lines.get(3)));
			}
		} catch(Exception ex) {
			Utils.exitWithMsg("Dogodila se greska tijekom parsiranja konfiguracijske datoteke!");
		}
		
		IFunction function = null;
		
		switch(functionNumber) {
		case 1:
			function = new Function1();
			break;
			
		case 2:
			function = new Function2();
			break;
			
		case 3:
			function = new Function3(params);
			break;
			
		case 4:
			function = new Function4();
			break;

		default:
			Utils.exitWithMsg("Nepoznata funkcija!");;
		}
		
		System.out.println("Odabrana funkcija: " + function);
		
		double[] solution = NumericalOptimisations.hookJeeves(
				function,
				startingPoint,
				dx,
				epsilon
				);
		
		System.out.println("Tocka minimuma: " + Arrays.toString(solution));
	}

}
