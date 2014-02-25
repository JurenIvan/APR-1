package hr.fer.apr.genetic;

import hr.fer.apr.opt.SingleObjectiveSolution;

/**
 * Sucelje koje predstavlja mutaciju u genetskom algoritmu
 * @author Luka Sterbic
 * @version 0.2
 * @param <T> tip jedinke
 */
public interface IMutation<T extends SingleObjectiveSolution> {
	
	/**
	 * Mutiraj zadanu jedinku
	 * @param t jedinka
	 */
	public void mutate(T chromosome);

}
