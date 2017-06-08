package de.group1.fruas.model;

import java.util.List;

public class Order {
	private int id;
	private boolean isCompleted;
	private List<MenuItem> menuItems;
	private Restaurant restaurant;
	private Customer customer;
	private DeliveryPersonnel deliverer;
	
	public Order() {
		
	}	
	
	public Order(boolean isCompleted, List<MenuItem> menuItems, Restaurant restaurant, Customer customer) {		
		this.isCompleted = isCompleted;
		this.menuItems = menuItems;
		this.restaurant = restaurant;
		this.customer = customer;		
	}
	
	public Order(boolean isCompleted, List<MenuItem> menuItems, Restaurant restaurant, Customer customer,
			DeliveryPersonnel deliverer) {		
		this.isCompleted = isCompleted;
		this.menuItems = menuItems;
		this.restaurant = restaurant;
		this.customer = customer;
		this.deliverer = deliverer;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public DeliveryPersonnel getDeliverer() {
		return deliverer;
	}
	public void setDeliverer(DeliveryPersonnel deliverer) {
		this.deliverer = deliverer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", isCompleted=" + isCompleted + ", menuItems=" + menuItems + ", restaurant="
				+ restaurant + ", customer=" + customer + ", deliverer=" + deliverer + "]";
	}
	
	

}
