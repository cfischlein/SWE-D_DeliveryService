package de.group1.fruas.clients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;

import de.group1.fruas.model.Address;
import de.group1.fruas.model.Customer;
import de.group1.fruas.model.LoginCredentials;
import de.group1.fruas.model.MenuItem;
import de.group1.fruas.model.Restaurant;

public class CustomerClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		LoginCredentials login = null;

		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/DeliveryService/api/");
		WebTarget customerTarget = baseTarget.path("customers");
		WebTarget restaurantTarget = baseTarget.path("restaurants");
		WebTarget menuTarget = baseTarget.path("restaurants/{restaurantId}/menuitems");
		//WebTarget singleCustomerTarget = customerTarget.path("{customerId}");

		System.out.println("Delivery Service REST API Test Client for Customers");
		int input = 0;
		boolean testing = true;		
		while (testing || (input <= 0 || input >= 5)) {
			printMenu();
			input = getUserInputInt(scan);			
			switch (input) {
			case 1:
				login = getLoginCredentialsFromUser(scan);
				String username = login.getUsername();
				String password = login.getPassword();
				List<Customer> customers = customerTarget.request().header("Authorization", encode(username, password))
						.get(new GenericType<List<Customer>>() {
						});
				customers.stream().filter(customer -> customer.getEmail().equals(username))
						.forEach(System.out::println);
				break;
			case 2:
				Customer customer = getCustomerData(scan);
				Response postResponse = customerTarget.request().post(Entity.json(customer));
				System.out.println(postResponse);
				break;
			case 3:
				List<Restaurant> restaurants = restaurantTarget.request().get(new GenericType<List<Restaurant>>() {
				});
				restaurants.stream().forEach(System.out::println);
				break;
			case 4:
				List<Restaurant> restaurants2 = restaurantTarget.request().get(new GenericType<List<Restaurant>>() {
				});
				List<Restaurant> availableRestaurants = restaurants2.stream()
						.filter(restaurant -> restaurant.isAvailable()).collect(Collectors.toList());
				System.out.println("Choose a restaurant:");
				for (int i = 0; i < availableRestaurants.size(); i++) {
					System.out.println((i + 1) + ")" + availableRestaurants.get(i));
				}
				int input2 = getUserInputInt(scan);
				Restaurant chosenRestaurant = availableRestaurants.get(input2 - 1);
				List<MenuItem> menuItems = menuTarget.resolveTemplate("restaurantId", chosenRestaurant.getId())
						.request().get(new GenericType<List<MenuItem>>() {
						});
				List<MenuItem> shoppingCart = new ArrayList<MenuItem>();
				System.out.println("Add to your shopping cart:");
				int input3 = 0;
				boolean shopping = true;
				while (shopping || (input3 <= 0 || input3 > menuItems.size() + 1)) {
					for (int i = 0; i < menuItems.size(); i++) {
						System.out.println((i + 1) + ")" + menuItems.get(i));
					}
					System.out.println(menuItems.size() + 1 + ")Finished");
					input3 = getUserInputInt(scan);
					if (input3 <= menuItems.size()) {
						shoppingCart.add(menuItems.get(input3 - 1));
					} else if (input3 <= 0 || input3 > menuItems.size() + 1) {
						System.out.println("Invalid input!");
					} else {
						System.out.println("Thanks for ordering:");
						shoppingCart.stream().forEach(System.out::println);
						// TODO CREATE ORDER RESOURCE WITH POST HERE
						shopping = false;
					}
				};
				break;
			case 5:
				testing = false;
				System.out.println("Goodbye.");
				return;
			}
		};

		client.close();

		scan.close();

	}

	private static Customer getCustomerData(Scanner scan) {
		System.out.println("First Name:");
		String firstname = getUserInputString(scan);
		System.out.println("Last Name:");
		String lastname = getUserInputString(scan);
		System.out.println("Email:");
		String email = getUserInputString(scan);
		System.out.println("Password:");
		String password = getUserInputString(scan);

		System.out.println("PostalCode:");
		String postalcode = getUserInputString(scan);
		System.out.println("City:");
		String city = getUserInputString(scan);
		System.out.println("Street:");
		String street = getUserInputString(scan);
		System.out.println("HouseNumber:");
		String housenumber = getUserInputString(scan);
		Address address = new Address(postalcode, city, street, housenumber);
		return new Customer(firstname, lastname, email, password, address);
	}

	public static void printMenu() {
		System.out.println("Do you want to ");
		System.out.println("1)Login");
		System.out.println("2)Register");
		System.out.println("3)Find Restaurants");
		System.out.println("4)Place Order");
		System.out.println("5)Exit");
	}

	public static int getUserInputInt(Scanner scan) {
		int input = 0;
		if (scan.hasNextInt()) {
			input = scan.nextInt();
		}
		return input;
	}

	public static String getUserInputString(Scanner scan) {
		String input = "";
		if (scan.hasNext()) {
			input = scan.next();
		}
		return input;
	}

	public static LoginCredentials getLoginCredentialsFromUser(Scanner scan) {
		System.out.println("Please login:");
		System.out.println("Enter username: ");
		String username = getUserInputString(scan);
		System.out.println("Enter password: ");
		String password = getUserInputString(scan);
		return new LoginCredentials(username, password);
	}

	public static String encode(String username, String password) {
		String getsEncoded = username + ":" + password;
		String encoded = new String(Base64.encodeBase64(getsEncoded.getBytes()));
		return encoded;
	}

}
