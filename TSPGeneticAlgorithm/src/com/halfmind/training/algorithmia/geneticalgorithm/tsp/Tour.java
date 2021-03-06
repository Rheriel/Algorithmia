/**
 * Following tutorial from:
 * 
 * http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5
 * 
 */
package com.halfmind.training.algorithmia.geneticalgorithm.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Routes are generally referred to as Tours.
 * 
 * @author Ban
 */
public class Tour {

	// Holds the tour of cities.
	private List<City> tour = new ArrayList<City>();
	
	// Cache
	private double fitness = 0;
	private int distance = 0;
	
	/**
	 * Creates an empty tour (no cities added yet).
	 */
	public Tour(){
		for (int i = 0; i < TourManager.numberOfCities(); i++) {
			tour.add(null);
		}
	}
	
	public Tour(List<City> tour){
		this.tour = tour;
	}
	
	public void generateIndividual(){
		// Loop through all the destination cities  and add them to the tour.
		for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
			setCity(cityIndex, TourManager.getCity(cityIndex));
		}
		
		// Randomly re-order the tour.
		Collections.shuffle(tour);
	}
	
	/**
	 * Gets a city from the tour.
	 * 
	 * @param tourPosition
	 * @return city
	 */
	public City getCity(int tourPosition){
		return tour.get(tourPosition);
	}
	
	/**
	 * Sets a city in a certain position within a tour.
	 * 
	 * @param tourPosition
	 * @param city
	 */
	public void setCity(int tourPosition, City city){
		tour.set(tourPosition, city);
		// If the tour has been altered, we need to reset the fitness and distance.
		fitness = 0;
		distance = 0;
	}
	
	/**
	 * Gets the tour's fitness.
	 * @return
	 */
	public double getFitness(){
		if (fitness == 0){
			fitness = 1/(double)getDistance();
		}
		return fitness;
	}
	
	/**
	 * Gets the total distance of the tour.
	 * @return
	 */
	public int getDistance(){
		if (distance == 0){
			int tourDistance = 0;
			// Loop through the tour's cities.
			for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
				// Get city we're traveling from.
				City fromCity = getCity(cityIndex);
				// Get city we're traveling to.
				City destinationCity;
				// Check we're not on our tour's last city, if we are, set 
				// our tour's final destination city to our starting city.
				if(cityIndex + 1 < tourSize()){
					destinationCity = getCity(cityIndex + 1);
				} else {
					destinationCity = getCity(0);
				}
				
				// Get the distance between the two cities.
				tourDistance += fromCity.distanceTo(destinationCity);
			}
			
			distance = tourDistance;
		}
		return distance;
	}
	
	/**
	 * Get the number of cities on our tour.
	 * @return
	 */
	public int tourSize(){
		return tour.size();
	}
	
	/**
	 * Check if the tour contains a city.
	 * 
	 * @param city
	 * @return
	 */
	public boolean containsCity(City city){
		return tour.contains(city);
	}
	
	@Override
	public String toString() {
		String geneString = "|";
		for (int i = 0; i < tourSize(); i++) {
			geneString += getCity(i) + "|";
		}
		return geneString;
	}
}
