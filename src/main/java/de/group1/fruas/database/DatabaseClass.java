package de.group1.fruas.database;

import java.util.HashMap;
import java.util.Map;

import de.group1.fruas.model.Customer;
import de.group1.fruas.model.Order;
import de.group1.fruas.model.Restaurant;

public class DatabaseClass {

	private static Map<Integer, Restaurant> restaurants = new HashMap<>();
	private static Map<Integer, Customer> customers = new HashMap<>();
	private static Map<Integer, Order> orders = new HashMap<>();
	
	public static Map<Integer, Order> getOrders() {
		return orders;
	}

	public static Map<Integer, Restaurant> getRestaurants() {
		return restaurants;
	}

	public static Map<Integer, Customer> getCustomers() {
		return customers;
	}
	
}
