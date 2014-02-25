package hr.fer.apr.numeric;

import hr.fer.apr.algebra.Matrix;
import hr.fer.apr.functions.IFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa koja predstavlja skup eksplicitnih i implicitnih ogranicenja
 * @author Luka Sterbic
 * @version 0.1
 */
public class Constraints {
	
	private int dimensions;
	private List<Constraint> constraints;
	private Matrix mins;
	private Matrix maxs;	
	
	/**
	 * Konstruktor za Constraints
	 * @param dimensions broj varijabli nad kojima su ogranicenja definirana
	 * @param mins vektor minimuma eksplicitnih ogranicenja
	 * @param maxs vektor maksimuma eksplicitnih ogranicenja
	 */
	public Constraints(int dimensions, Matrix mins, Matrix maxs) {
		if(dimensions < 1) {
			throw new IllegalArgumentException("Broj varijabli mora biti veci od 1!");
		}
		
		if(mins == null || maxs == null) {
			throw new IllegalArgumentException(
					"Vektori ekplicitnih ogranicenja ne smiju biti null!");
		}
		
		this.dimensions = dimensions;
		this.constraints = new ArrayList<>();
		this.mins = mins;
		this.maxs = maxs;
	}

	/**
	 * Provjeri tocku za eksplicitna ogranicenja i korigiraj ako je potrebno
	 * @param point zadana tocka
	 * @return true ako je doslo do korekcije, false inace
	 */
	public boolean checkExplicit(Matrix point) {
		checkPoint(point);
		boolean edited = false;
		
		for(int i = 0; i < dimensions; i++) {
			double value = point.get(i, 0);
			
			if(value < mins.get(i, 0)) {
				point.set(i, 0, mins.get(i, 0));
			} else if(value > maxs.get(i, 0)) {
				point.set(i, 0, maxs.get(i, 0));
			}
		}
		
		return edited;
	}
	
	/**
	 * Dodaj implicitno ogranicenje u listu ogranicenja
	 * @param function funkcija ogranicenja
	 * @param lessThan true ako je ogranicenje zadovoljeno
	 * kada je vrijednost funkcije manja od nule, false inace
	 */
	public void addConstrint(IFunction function, boolean lessThan) {
		constraints.add(new Constraint(function, lessThan));
	}
	
	/**
	 * Provjeri da li su ogranicenja zadovoljena za odredjenu tocku
	 * @param point zadana tocka
	 * @return true ako su ogranicenja zadovoljena, false inace
	 */
	public boolean areSatisfied(Matrix point) {
		checkPoint(point);
		
		for(Constraint con : constraints) {
			if(!con.isSatisfied(point)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Vrati random tocku koja zadovoljava eksplicitna ogranicenja
	 * @return slucajna tocka
	 */
	public Matrix randomPoint() {
		Random rand = new Random();
		double[] random = new double[dimensions];
		
		for(int i = 0; i < dimensions; i++) {
			double min = mins.get(i, 0);
			double max = maxs.get(i, 0);
			
			random[i] = min + rand.nextDouble() * (max - min);
		}
		
		return new Matrix(random);
	}
	
	/**
	 * Provjeri da li je format zadane tocke legalan
	 * @param point zadana tocka
	 */
	private void checkPoint(Matrix point) {
		if(point == null || !point.isVector(dimensions)) {
			throw new IllegalArgumentException("Zadana tocka nije legalna!");
		}
	}

	/**
	 * Privatna klasa koja modelira jedno ogranicenje
	 * oblika f(x) <= 0 ili f(x) >= 0
	 * @author Luka Sterbic
	 * @version 0.1
	 */
	private class Constraint {
		
		private IFunction function;
		private boolean lessThan;
		
		/**
		 * Konstruktor za Constraint
		 * @param function funkcija ogranicenja
		 * @param lessThan true ako je ogranicenje zadovoljeno
		 * kada je vrijednost funkcije manja od nule, false inace
		 */
		public Constraint(IFunction function, boolean lessThan) {
			this.function = function;
			this.lessThan = lessThan;
		}
		
		/**
		 * Provjeri da li je ovo ogranicenje zadovoljeno za zadanu tocku
		 * @param point zadana tocka
		 * @return true ako je ogranicenje zadovoljeno, false inace
		 */
		public boolean isSatisfied(Matrix point) {
			double value = function.value(point);
			
			if(lessThan && value <= 0 || !lessThan && value >= 0) {
				return true;
			} else {
				return false;
			}
		}
		
	}
	
}
