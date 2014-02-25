package hr.fer.apr.genetic;

import hr.fer.apr.opt.MatrixSolution;

/**
 * Sucelje koje predstavlja krizanje u genetskom algoritmu
 * @author Luka Sterbic
 * @version 0.1
 */
public interface ICrossover {
	
	/**
	 * Stvori novu jedinku krizanjem dvaju roditelja
	 * @param firstParent prvi roditalj
	 * @param secondParent drugi roditelj
	 * @return nova jedinka
	 */
	public MatrixSolution cross(MatrixSolution firstParent, MatrixSolution secondParent);

}
