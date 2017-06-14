package de.group1.fruas.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.group1.fruas.database.DatabaseClass;
import de.group1.fruas.model.Address;
import de.group1.fruas.model.MenuItem;
import de.group1.fruas.model.RegularMembership;
import de.group1.fruas.model.Restaurant;
import de.group1.fruas.model.Feedback;

public class RestaurantService {
	
	private Map<Integer, Restaurant> restaurants = DatabaseClass.getRestaurants();
	
	
	public RestaurantService() {
		HashMap<Integer, MenuItem> menu1 = new HashMap<Integer, MenuItem>();
		menu1.put(1, new MenuItem(1, "Large Pizza", 5.5));
		menu1.put(2, new MenuItem(2, "Small Pizza", 4.4));
		HashMap<Integer, MenuItem> menu2 = new HashMap<Integer, MenuItem>();
		menu1.put(1, new MenuItem(1, "Large Burger", 7.7));
		menu1.put(2, new MenuItem(2, "Small Burger", 6.6));
		restaurants.put(1, new Restaurant(1, "Tony's Pizza Place", "secretpw", "pizza@tony.com",
				new Address("12345", "PizzaTown", "PastaStreet", "1"), new RegularMembership(), new HashMap<Integer, Feedback>(), menu1, true));
		
		restaurants.put(2, new Restaurant(2, "Bob's Burger Bar", "12345", "burger@bob.com", 
				new Address("54321", "BurgerTown", "FriesStreet", "2"), new RegularMembership(), new HashMap<Integer, Feedback>(), menu2, true));
		
		restaurants.put(3, new Restaurant(3, "Susi's Sushi Store", "swordfish", "sushi@susi.net", 
				new Address("3333", "FishCity", "ShrimpStreet", "33a"), new RegularMembership(), new HashMap<Integer, Feedback>(),new HashMap<Integer, MenuItem>(), false));
	}
	
	public List<Restaurant> getAllRestaurants() {
		return new ArrayList<Restaurant>(restaurants.values());
	}

	public Restaurant addRestaurant(Restaurant restaurant) {
		restaurant.setId(restaurants.size()+1);
		restaurants.put(restaurant.getId(), restaurant);
		return restaurant;
	}

	public Restaurant deleteRestaurant(int id) {
		return restaurants.remove(id);		
	}

	public Restaurant editRestaurant(Restaurant restaurant) {
		if(restaurant.getId() <= 0) {
			return null;
		}
		restaurants.put(restaurant.getId(), restaurant);
		return restaurant;
	}

	public Restaurant getRestaurant(int id) {		
		return restaurants.get(id);
	}	

}
