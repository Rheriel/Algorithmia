/**
 * Following tutorial from:
 * 
 * http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3
 * 
 */
package com.halfmind.training.algorithmia.geneticalgorithm;

/**
 * Manages our evolution algorithms such as crossover and mutation.
 * 
 * @author Ban
 *
 */
public class Algorithm {

	/* Genetic Algorithm Parameters */
	private static final double uniformRate = 0.0;
	private static final double mutationRate = 0.015;
	private static final int tournamentSize = 5;
	private static final boolean elitism = true;

	/**
	 * The basic process for a genetic algorithm is:
	 * 
	 * Initialization - Create an initial population. This population is usually randomly generated and can be any desired size, from only a few individuals to thousands.
	 * Evaluation - Each member of the population is then evaluated and we calculate a 'fitness' for that individual. The fitness value is calculated by how well it fits with our desired requirements. These requirements could be simple, 'faster algorithms are better', or more complex, 'stronger materials are better but they shouldn't be too heavy'.
	 * Selection - We want to be constantly improving our populations overall fitness. Selection helps us to do this by discarding the bad designs and only keeping the best individuals in the population.  There are a few different selection methods but the basic idea is the same, make it more likely that fitter individuals will be selected for our next generation.
	 * Crossover - During crossover we create new individuals by combining aspects of our selected individuals. We can think of this as mimicking how sex works in nature. The hope is that by combining certain traits from two or more individuals we will create an even 'fitter' offspring which will inherit the best traits from each of it's parents.
	 * Mutation - We need to add a little bit randomness into our populations' genetics otherwise every combination of solutions we can create would be in our initial population. Mutation typically works by making very small changes at random to an individuals genome.
	 * And repeat! - Now we have our next generation we can start again from step two until we reach a termination condition.
	 * 
	 * @param population
	 * @return
	 */
	public static Population evolvePopulation(Population population){
		Population newPopulation = new Population(population.size(), false);

		// Keep our best individual.
		if(elitism){
			newPopulation.saveIndividual(0, population.getFittest());
		}

		// Crossover population.
		int elitismOffset;
		if(elitism){
			elitismOffset = 1;
		} else {
			elitismOffset = 0;
		}

		// Loop over population and create new individuals with crossover.
		for (int i = elitismOffset; i < population.size(); i++) {
			Individual individual1 = tournamentSelection(population);
			Individual individual2 = tournamentSelection(population);
			Individual newIndividual = crossover(individual1, individual2);
			newPopulation.saveIndividual(i, newIndividual);
		}

		// Mutate population.
		for (int i = elitismOffset; i < newPopulation.size(); i++) {
			mutate(newPopulation.getIndividual(i));
		}

		return newPopulation;
	}

	/**
	 *  Select individuals for crossover.
	 *  
	 * @param population to get random individuals for the tournament (selecting fittest of them all).
	 * @return Will return the fittest individual in the population.
	 */
	private static Individual tournamentSelection(Population population) {
		// Create tournament population.
		Population tournament = new Population(tournamentSize, false);
		// For each place in the tournament get a random individual.
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * population.size());
			tournament.saveIndividual(i, population.getIndividual(randomId));
		}
		// Get the fittest.
		Individual fittest = tournament.getFittest();
		return fittest;
	}

	/**
	 * Crossovers individuals.
	 * 
	 * @param individual1
	 * @param individual2
	 * 
	 * @return
	 */
	private static Individual crossover(Individual individual1,
			Individual individual2) {

		Individual newSolution = new Individual();
		for (int i = 0; i < individual1.size(); i++) {
			// Crossover.
			if(Math.random() <= uniformRate){
				newSolution.setGene(i, individual1.getGene(i));
			} else {
				newSolution.setGene(i, individual2.getGene(i));
			}
		}
		return newSolution;
	}

	/**
	 * Mutates an individual.
	 * 
	 * @param individual
	 */
	private static void mutate(Individual individual) {
		// Loops through genes.
		for (int i = 0; i < individual.size(); i++) {
			if(Math.random() <= mutationRate){
				// Create random gene.
				byte gene = (byte) Math.round(Math.random());
				individual.setGene(i, gene);
			}
		}
	}

}
