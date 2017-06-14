package de.group1.fruas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.group1.fruas.database.DatabaseClass;
import de.group1.fruas.model.Address;
import de.group1.fruas.model.Customer;

public class CustomerService implements Service<Customer>{

	private Map<Integer, Customer> customers = DatabaseClass.getCustomers();

	public CustomerService() {
		customers.put(1, new Customer(1, "Bruce", "Wayne", "bw@bat.com", "ih8joker",
				new Address("GothamCity", "1234", "BatcaveSt.", "1")));
		customers.put(2, new Customer(2, "Peter", "Parker", "pp@spider.net", "i<3mj",
				new Address("New York", "1234", "ForestHills", "12")));
		customers.put(3, new Customer(3, "test", "user", "asdf", "fdsa",
				new Address("city", "1234", "street", "221")));
	}

	public List<Customer> getAllItems() {
		return new ArrayList<Customer>(customers.values());
	}
	@Override
	public Customer addItem(Customer customer) {
		customer.setId(customers.size() + 1);
		customers.put(customer.getId(), customer);
		return customer;
	}

	public Customer deleteItem(int id) {
		return customers.remove(id);
	}
	@Override
	public Customer editItem(Customer customer) {
		if (customer.getId() <= 0) {
			return null;
		}
		customers.put(customer.getId(), customer);
		return customer;
	}

	public Customer getItem(int id) {
		Customer customer = customers.get(id);
		return customer;
	}
	
	
}
