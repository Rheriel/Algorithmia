/**
 * Following tutorial from:
 * 
 * http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5
 * 
 */
package com.halfmind.training.algorithmia.geneticalgorithm.tsp;

/**
 * Represents a city.
 * 
 * @author Ban
 *
 */
public class City {
	
	private int x;
	private int y;
	
	/**
	 * Constructs a randomly placed city.
	 */
	public City(){
		this.x = (int)(Math.random() * 200);
		this.y = (int)(Math.random() * 200);
	}

	/**
	 * Constructs a city in a given position.
	 * @param x
	 * @param y
	 */
	public City(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	/**
	 * Gets the distance to a given city.
	 * 
	 * @param city
	 * @return double Distance.
	 */
	public double distanceTo(City city){
		int xDistance = Math.abs(getX() - city.getX());
		int yDistance = Math.abs(getY() - city.getY());
		double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
		
		return distance;
	}
	
	@Override
	public String toString() {
		return getX() + ", " + getY();
	}
	
	
	
}
