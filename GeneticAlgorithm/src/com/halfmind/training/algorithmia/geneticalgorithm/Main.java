/**
 * Following tutorial from:
 * 
 * http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3
 * 
 */
package com.halfmind.training.algorithmia.geneticalgorithm;

/**
 * Runner class.
 * 
 * @author Ban
 *
 */
public class Main {

	public static void main(String[] args) {
		
		// First we need to set a candidate solution (feel free to change this if you want to).
		FitnessCalculator.setSolution("1111000000000000000000000000000000000000000000000000000000001111");
		// Now we'll create our initial population, a population of 50 should be fine. 
		Population myPopulation = new Population(50, true);
		// Now we can evolve our population until we reach our optimum fitness.
		int generationCount = 0;
		while (myPopulation.getFittest().getFitness() < FitnessCalculator.getMaxFitness()) {
			generationCount++;
			System.out.println("Generation: " + generationCount + " Fittest: " + myPopulation.getFittest().getFitness());
			myPopulation = Algorithm.evolvePopulation(myPopulation);
		}
		System.out.println("Solution found!");
		System.out.println("Genaration: " + generationCount);
		System.out.println("Genes: ");
		System.out.println(myPopulation.getFittest());
	}
	
}
