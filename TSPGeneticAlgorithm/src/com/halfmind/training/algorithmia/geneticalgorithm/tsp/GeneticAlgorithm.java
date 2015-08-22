/**
 * Following tutorial from:
 * 
 * http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5
 * 
 */
package com.halfmind.training.algorithmia.geneticalgorithm.tsp;

/**
 * This class handles the genetic algorithm and evolving of population.
 * 
 * @author Ban
 *
 */
public class GeneticAlgorithm {
	
	private static final double MUTATION_RATE = 0.015;
	private static final int TOURNAMENT_SIZE = 5;
	private static final boolean ELITISM = true;
	
	/**
	 * Evolves a population over one generation.
	 * @param pop
	 * @return
	 */
	public static Population evolvePopulation(Population pop) {
		Population newPopulation = new Population(pop.populationSize(), false);
		// Keep our best individual if elitism is enabled.
		int elitismOffset = 0;
		if (ELITISM) {
			newPopulation.saveTour(0, pop.getFittest());
			elitismOffset = 1;
		}
		
		/* 
		* Crossover population.
		* Loop over the new population's size and create individuals 
		* from current population.
		*/
		for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
			// Select parents.
			Tour parent1 = tournamentSelection(pop);
			Tour parent2 = tournamentSelection(pop);
			// Crossover parents.
			Tour child = crossover(parent1, parent2);
			// Add child to new population.
			newPopulation.saveTour(i, child);
		}
		
		// Mutate the new population a bit to add some new genetic material.
		for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
			mutate(newPopulation.getTour(i));
		}
		
		return newPopulation;
	}
	
	/**
	 * Applies crossover to a set of parents and creates an offspring.
	 * @param parent1
	 * @param parent2
	 * @return
	 */
	public static Tour crossover(Tour parent1, Tour parent2){
		// Create new child tour.
		Tour child = new Tour();
		
		// Gets a new child in tour.
		int startPos = (int) (Math.random() * parent1.tourSize());
		int endPos = (int) (Math.random() * parent1.tourSize());
		
		// Loop and add the sub tour from parent1 to our child.
		for (int i = 0; i < child.tourSize(); i++) {
			// If our start position is less than the end position.
			if ((startPos < endPos) &&
					(i > startPos) &&
					(i < endPos)){
				child.setCity(i, parent1.getCity(i));
			} else if (startPos > endPos){
				if (!(i < startPos && i > endPos)){
					child.setCity(i, parent1.getCity(i));
				}
			}
		}
		
		// Loop through parent2's city tour.
		for (int i = 0; i < parent2.tourSize(); i++) {
			// If child doesn't have the city add it.
			if(!child.containsCity(parent2.getCity(i))){
				// Loop to find a spare position in the child's tour.
				for (int ii = 0; ii < child.tourSize(); ii++) {
					// Spare position found, add city.
					if (child.getCity(ii) == null){
						child.setCity(ii, parent2.getCity(i));
						break;
					}
				}
			}
		}
		return child;
	}
	
	/**
	 * Mutate a tour using swap mutation.
	 * @param tour
	 */
	private static void mutate(Tour tour){
		// Loop through tour cities.
		for (int tourPos1 = 0; tourPos1 < tour.tourSize(); tourPos1++) {
			// Apply mutation rate.
			if (Math.random() < MUTATION_RATE){
				// Get a second random position in the tour.
				int tourPos2 = (int) (tour.tourSize() * Math.random());
				
				// Get the cities at target position in tour.
				City city1 = tour.getCity(tourPos1);
				City city2 = tour.getCity(tourPos2);
				
				// Swap them around.
				tour.setCity(tourPos2, city1);
				tour.setCity(tourPos1, city2);
			}
		}
	}
	
	/**
	 * Selects candidate tour for crossover.
	 * @param pop
	 * @return
	 */
	private static Tour tournamentSelection(Population pop) {
		// Create a tournament population.
		Population tournament = new Population(TOURNAMENT_SIZE, false);
		// For each place in the tournament get a random candidate tour and
		// add it.
		for (int i = 0; i < TOURNAMENT_SIZE; i++) {
			int randomId = (int) (Math.random() * pop.populationSize());
			tournament.saveTour(i, pop.getTour(randomId));
		}
		// Get the fittest tour.
		Tour fittest = tournament.getFittest();
		return fittest;
	}

}
