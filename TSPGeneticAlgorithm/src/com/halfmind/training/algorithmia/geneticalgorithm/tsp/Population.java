/**
 * Following tutorial from:
 * 
 * http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5
 * 
 */
package com.halfmind.training.algorithmia.geneticalgorithm.tsp;


/**
 * Holds a population of candidate tours.
 * 
 * @author Ban
 */
public class Population {
	
	// Holds a population of tours.
	Tour[] tours;
	
	/**
	 * Construct a population.
	 * @param populationSize
	 * @param initialize
	 */
	public Population(int populationSize, boolean initialize){
		tours = new Tour[populationSize];
		// If we need to initialize a population of tours, do so.
		if(initialize){
			// Loop to create individuals.
			for (int i = 0; i < populationSize(); i++) {
				Tour newTour = new Tour();
				newTour.generateIndividual();
				saveTour(i, newTour);
			}
		}
	}
	
	/**
	 *  Saves a tour into the population.
	 * @param index
	 * @param tour
	 */
	public void saveTour(int index, Tour tour){
		tours[index] = tour;
	}
	
	/**
	 *  Gets a tour from population.
	 * @param index
	 * @return
	 */
	public Tour getTour(int index){
		return tours[index];
	}
	
	/**
	 * Gets the best tour in the population.
	 * @return
	 */
	public Tour getFittest(){
		Tour fittest = tours[0];
		// Loop through individuals to find fittest.
		for (int i = 0; i < populationSize(); i++) {
			if (fittest.getFitness() <= getTour(i).getFitness()){
				fittest = getTour(i);
			}
		}
		return fittest;
	}
	
	/**
	 * Gets population size.
	 * 
	 * @return
	 */
	public int populationSize(){
		return tours.length;
	}

}
