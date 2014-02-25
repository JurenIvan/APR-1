package hr.fer.apr.numeric;

import hr.fer.apr.algebra.Matrix;
import hr.fer.apr.functions.IFunction;

/**
 * Klasa koja sadrzi skup staticki metoda za numericku optimizacije
 * @author Luka Sterbic
 * @version 0.2
 */
public class NumericalOptimisations {
	
	/**
	 * Numericka optimizacija funkcije po postupku Hook-Jeeves
	 * @param function funkcija koja se optimira
	 * @param startingPoint pocetna tocka pretrazivanja
	 * @param dx vektor pomaka
	 * @param epsilon vektor uvjeta zaustavljanja
	 * @return pronadjeni minimum funkcije
	 */
	public static double[] hookJeeves(IFunction function, Matrix startingPoint,
			Matrix dx, Matrix epsilon) {
		if(!startingPoint.isVector(function.dimensions())) {
			throw new IllegalArgumentException("Ilegalan format pocetne tocke!");
		}
		
		if(!dx.isVector(function.dimensions())) {
			throw new IllegalArgumentException("Ilegalan format vektora pomaka!");
		}
		
		if(!epsilon.isVector(function.dimensions())) {
			throw new IllegalArgumentException("Ilegalan format vektora greske!");
		}
		
		Matrix current = startingPoint.copy();
		Matrix base = startingPoint.copy();
		
		while(true) {
			Matrix newPoint = HJFind(function, current, dx);
			
			if(function.value(newPoint) < function.value(base)) {
				current = newPoint.copy().multiply(2).subtractToThis(base);
				base = newPoint;
			} else {
				dx.multiplyToThis(0.5);
				current = base.copy();
				
				for(int i = 0; i < epsilon.getRows(); i++) {
					if(epsilon.get(i, 0) < dx.get(i, 0)) {
						break;
					} else if(i == epsilon.getRows() - 1) {
						return base.columnAsArray(0);
					}
				}
			}
		}
	}

	/**
	 * Trazi sljedecu tocku u Hook-Jeevesovom postupku
	 * @param function funkcija koja se optimira
	 * @param point trenutna tocka
	 * @param dx vektor pomaka
	 * @return sljedeca tocka u postupku
	 */
	private static Matrix HJFind(IFunction function, Matrix point, Matrix dx) {
		Matrix newPoint = point.copy();
		
		for(int i = 0; i < newPoint.getRows(); i++) {
			double P = function.value(newPoint);
			double dXi = dx.get(i, 0);
			
			newPoint.setAdd(i, 0, dXi);
			
			double N = function.value(newPoint);
			
			if(N > P) {
				newPoint.setAdd(i, 0, -2 * dXi);
				
				N = function.value(newPoint);
				
				if(N > P) {
					newPoint.setAdd(i, 0, dXi);
				}
			}
		}
		
		return newPoint;
	}

	/**
	 * Numericka optimizacija funkcije po Boxu
	 * @param function funkcija koja se optimira
	 * @param startingPoint pocetna tocka pretrazivanja
	 * @param alpha faktor alfa
	 * @param epsilon vektor uvjeta zaustavljanja
	 * @param constraints objekt koji predstavlja sva ogranicenja
	 * @return pronadjeni minimum funkcije
	 */
	public static double[] box(IFunction function, Matrix startingPoint,
			double alpha, Matrix epsilon, Constraints constraints) {
		if(constraints.checkExplicit(startingPoint)) {
			System.err.println("Pocetna tocka ne zadovoljava eksplicitna ogranicenja!");
			return null;
		}
		
		if(!constraints.areSatisfied(startingPoint)) {
			System.err.println("Pocetna tocka ne zadovoljava implicitna ogranicenja!");
			return null;
		}
		
		int n = 2 * function.dimensions();
		Matrix[] points = new Matrix[n];
		points[0] = startingPoint;
		
		Matrix Xc = startingPoint.copy();
		
		for(int t = 1; t < n; t++) {
			Matrix next = constraints.randomPoint();
			
			while(!constraints.areSatisfied(next)) {
				next = next.add(Xc).multiplyToThis(0.5);
			}
			
			points[t] = next;
			Xc = getCentroid(points);
		}
		
		while(true) {
			int h = 0;
			int h2 = 1;
			
			double[] values = new double[n];
			for(int i = 0; i < n; i++) {
				values[i] = function.value(points[i]);
			}
			
			if(values[1] > values[0]) {
				int temp = h;
				h = h2;
				h2 = temp;
			}
			
			for(int i = 2; i < n; i++) {
				if(values[i] > values[h]) {
					h2 = h;
					h = i;
				}
			}
			
			Xc = getCentroid(points, h);
			
			Matrix Xr = Xc.multiply(1 + alpha).subtractToThis(points[h].multiply(alpha));
			
			constraints.checkExplicit(Xr);
			
			while(!constraints.areSatisfied(Xr)) {
				Xr = Xr.add(Xc).multiplyToThis(0.5);
			}
			
			if(function.value(Xr) > function.value(points[h2])) {
				Xr = Xr.add(Xc).multiplyToThis(0.5);
			}
			
			points[h] = Xr;
			
			Matrix diff = points[h].subtract(Xc);
			for(int i = 0; i < function.dimensions(); i++) {
				if(Math.abs(diff.get(i, 0)) > epsilon.get(i, 0)) {
					break;
				} else if(i == function.dimensions() - 1) {
					return Xc.columnAsArray(0);
				}
			}
		}
	}
	
	/**
	 * Vrati centroid zadanih tocaka
	 * @param points polje tocaka
	 * @return tocka centroida
	 */
	private static Matrix getCentroid(Matrix[] points) {
		return getCentroid(points, -1);
	}
	
	/**
	 * Vrati centroid zadanih tocaka
	 * @param points polje tocaka
	 * @param skipIndex preskoci tocku na zadanom indeksu
	 * @return tocka centroida
	 */
	private static Matrix getCentroid(Matrix[] points, int skipIndex) {
		Matrix centroid = points[0].copy();
		int nPoints = 1;
		
		for(int i = 1; i < points.length; i++) {
			if(skipIndex == i || points[i] == null) {
				continue;
			}
			
			centroid.addToThis(points[i]);
			nPoints++;
		}
		
		return centroid.multiply(1.0 / nPoints);
	}

}
