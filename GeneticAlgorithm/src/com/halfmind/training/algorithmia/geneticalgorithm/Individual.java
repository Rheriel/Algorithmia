/**
 * Following tutorial from:
 * 
 * http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3
 * 
 */
package com.halfmind.training.algorithmia.geneticalgorithm;

/**
 * Manages an individual.
 * 
 * @author Ban
 *
 */
public class Individual {
	
	static int defaultGeneLength = 64;
	private byte[] genes = new byte[defaultGeneLength];
	
	// Cache
	private int fitness = 0;

	public void generateIndividual() {
		for (int i = 0; i < size(); i++) {
			byte gene = (byte) Math.round(Math.random());
			genes[i] = gene;
		}
	}

	public int size() {
		return genes.length;
	}

	public int getFitness() {
		if(fitness == 0){
			fitness = FitnessCalculator.getFitness(this);
		}
		return fitness;
	}
	
	// Use this if you want to create individuals with different gene length.
	public static void setDefaultGeneLength(int length){
		defaultGeneLength = length;
	}
	
	public byte getGene(int index){
		return genes[index];
	}

	public void setGene(int index, byte gene){
		genes[index] = gene;
		fitness = 0;
	}
	
	@Override
	public String toString(){
		String geneString = "";
		for (int i = 0; i < size(); i++) {
			geneString += getGene(i);
		}
		return geneString;
	}
}
