package de.group1.fruas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.group1.fruas.database.DatabaseClass;
import de.group1.fruas.model.Address;
import de.group1.fruas.model.Customer;
import de.group1.fruas.model.Order;
import de.group1.fruas.model.Restaurant;

public class CustomerService {

	private Map<Integer, Customer> customers = DatabaseClass.getCustomers();

	public CustomerService() {
		customers.put(1, new Customer(1, "Bruce", "Wayne", "bw@bat.com", "ih8joker",
				new Address("GothamCity", "1234", "BatcaveSt.", "1")));
		customers.put(2, new Customer(2, "Peter", "Parker", "pp@spider.net", "i<3mj",
				new Address("New York", "1234", "ForestHills", "12")));
		customers.put(3, new Customer(3, "asdf", "fdsa", "asdf", "fdsa",
				new Address("haha", "15224", "hjdfjdf", "221")));
	}

	public List<Customer> getAllCustomers() {
		return new ArrayList<Customer>(customers.values());
	}

	public Customer addCustomer(Customer customer) {
		customer.setId(customers.size() + 1);
		customers.put(customer.getId(), customer);
		return customer;
	}

	public Customer deleteCustomer(int id) {
		return customers.remove(id);
	}

	public Customer editCustomer(Customer customer) {
		if (customer.getId() <= 0) {
			return null;
		}
		customers.put(customer.getId(), customer);
		return customer;
	}

	public Customer getCustomer(int id) {
		Customer customer = customers.get(id);
		return customer;
	}

	public Restaurant findRestaurants() {
		return null;
	}

	public void placeOrder(Order order) {

	}

}
