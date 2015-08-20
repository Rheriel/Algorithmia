/**
 * Following tutorial from:
 * 
 * http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5
 * 
 */
package com.halfmind.training.algorithmia.geneticalgorithm.tsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the cities of a tour.
 * 
 * @author Ban
 */
public class TourManager {

	/**
	 * Holds the cities of the tour.
	 */
	private static List<City> destinationCities = new ArrayList<City>();
	
	/**
	 * Adds a destination city.
	 * 
	 * @param city
	 */
	public static void addCity(City city){
		destinationCities.add(city);
	}

	public static City getCity(int index){
		return destinationCities.get(index);
	}
	
	public static int numberOfCities(){
		return destinationCities.size();
	}
	
}
