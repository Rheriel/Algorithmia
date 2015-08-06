/**
 * Following tutorial from:
 * 
 * http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3
 * 
 */
package com.halfmind.training.algorithmia.geneticalgorithm;

/**
 * Manages all the individuals of a population.
 * 
 * @author Ban
 *
 */
public class Population {
	
	Individual[] individuals;
	
	/**
	 * Constructor.
	 * 
	 * @param populationSize
	 * @param initialize
	 */
	public Population(int populationSize, boolean initialize){
		individuals = new Individual[populationSize];
		if (initialize){
			for (int i = 0; i < size(); i++){
				Individual newIndividual = new Individual();
				newIndividual.generateIndividual();
				saveIndividual(i, newIndividual);
			}
		}
	}

	/**
	 * Gets size of the population.
	 * 
	 * @return Size of the population.
	 */
	public int size() {
		return individuals.length;
	}
	
	/**
	 * Puts an individual into the population.
	 * 
	 * @param index Place in the population.
	 * @param individual The individual.
	 */
	public void saveIndividual(int index, Individual individual) {
		individuals[index] = individual;
	}

	/**
	 * Gets individual from population.
	 * 
	 * @param index Index of the individual to get.
	 * @return An individual.
	 */
	public Individual getIndividual(int index){
		return individuals[index];
	}
	
	public Individual getFittest(){
		Individual fittest = individuals[0];
		
		for (int i = 0; i < size(); i++) {
			if(fittest.getFitness() <= getIndividual(i).getFitness()){
				fittest = getIndividual(i);
			}
		}
		return fittest;
	}

}
