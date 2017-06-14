package de.group1.fruas.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.group1.fruas.database.DatabaseClass;
import de.group1.fruas.model.Address;
import de.group1.fruas.model.Customer;
import de.group1.fruas.model.DeliveryPersonnel;
import de.group1.fruas.model.Feedback;
import de.group1.fruas.model.MenuItem;
import de.group1.fruas.model.Order;
import de.group1.fruas.model.RegularMembership;
import de.group1.fruas.model.Restaurant;

public class OrderService {

	private Map<Integer, Order> orders = DatabaseClass.getOrders();
	
	public OrderService() {
		List<MenuItem> items = new ArrayList<MenuItem>();
		items.add(new MenuItem(1, "Big Burger", 3.44));
		items.add(new MenuItem(1, "Small Coke", 1.66));
		Restaurant restaurant = new Restaurant(1, "Tony's Pizza Place", "secretpw", "pizza@tony.com",
				new Address("12345", "PizzaTown", "PastaStreet", "1"), new RegularMembership(), 
				new HashMap<Integer, Feedback>(),new HashMap<Integer, MenuItem>(), true);
		Customer customer = new Customer(1, "Bruce", "Wayne", "bw@bat.com", "ih8joker",
				new Address("GothamCity", "1234", "BatcaveSt.", "1"));
		orders.put(1, new Order(false, items, restaurant, customer, new DeliveryPersonnel()));
	}
	
	public List<Order> getAllOrders() {
		return new ArrayList<Order>(orders.values());
	}
	
	public Order addOrder(Order order) {
		order.setId(orders.size()+1);
		orders.put(order.getId(), order);
		return order;
	}
	
	public Order deleteOrder(int orderId) {
		return orders.remove(orderId);
	}
	
	public Order editOrder(Order order) {
		if(order.getId() <= 0) {
			return null;
		}
		orders.put(order.getId(), order);
		return order;
	}
	
	public Order getOrder(int orderId) {
		return orders.get(orderId);
	}
}
