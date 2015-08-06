/**
 * Following tutorial from:
 * 
 * http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3
 * 
 */
package com.halfmind.training.algorithmia.geneticalgorithm;

/**
 * Allow us to set a candidate solution and calculate an individual's fitness.
 * 
 * @author Ban
 *
 */
public class FitnessCalculator {

	static byte[] solution = new byte[64];
	
	/**
	 * Set a candidate solution as a byte array.
	 * 
	 * @param newSolution byte array for new solution.
	 */
	public static void setSolution(byte[] newSolution){
		solution = newSolution;
	}
	
	/**
	 * To make it easier we set our candidate solution with a string of 0s and 1s.
	 * 
	 * @param newSolution String of 0s and 1s to represent a byte array.
	 */
	public static void setSolution(String newSolution){
		solution = new byte[newSolution.length()];
		for (int i = 0; i < newSolution.length(); i++) {
			String character = newSolution.substring(i, i + 1);
			if(character.contains("0") || character.contains("1")){
				solution[i] = Byte.parseByte(character);
			} else {
				solution[i] = 0;
			}
		}
	}
	
	/**
	 * Calculate individual fitness by comparing it to our solution.
	 * 
	 * @param individual
	 * @return fitness
	 */
	public static int getFitness(Individual individual) {
		int fitness = 0;
		// Loop through our individuals genes to compare them to our candidates.
		for (int i = 0; i < individual.size() && i < solution.length; i++) {
			if(individual.getGene(i) == solution[i]){
				fitness++;
			}
		}
		return fitness;
	}
	
	/**
	 * Get optimum fitness.
	 * 
	 * @return Optimum fitness.
	 */
	public static int getMaxFitness(){
		int maxFitness = solution.length;
		return maxFitness;
	}

}
