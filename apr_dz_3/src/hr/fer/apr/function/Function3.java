package hr.fer.apr.function;

import hr.fer.apr.algebra.Matrix;

/**
 * Implementacija funkcije f3(x1, x2, x3, x4, x5) =
 * (x1 - p1)^2 + (x2 - p2)^2 + ... + (x5 - p5)^2 
 * @author Luka Sterbic
 * @version 0.3
 */
public class Function3 extends AbstractFunction {

	private Matrix params;
	
	/**
	 * Konstruktor za Function3
	 * @param params vektor parametra
	 */
	public Function3(Matrix params) {
		if(!params.isVector(dimensions())) {
			throw new IllegalArgumentException("Ilegalan format parametra!");
		}
		
		this.params = params;
	}
	
	@Override
	public int dimensions() {
		return 5;
	}

	@Override
	public double valueAt(Matrix point) {
		Matrix value = point.copy().subtractToThis(params);
		return value.transpose().multiply(value).get(0, 0);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("f3(x1, x2, x3, x4, x5) = ");
		
		for(int i = 0; i < dimensions(); i++) {
			double px = params.get(i, 0);
			
			sb.append("(x").append(i + 1);
			sb.append(px > 0 ? " - " : " + ");
			sb.append(Math.abs(px)).append(")^2");
			
			if(i != dimensions() - 1) {
				sb.append(" + ");
			}
		}
		
		return sb.toString();
	}

}
