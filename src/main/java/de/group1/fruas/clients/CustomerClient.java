package de.group1.fruas.clients;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import de.group1.fruas.model.Order;
import de.group1.fruas.model.Restaurant;

public class CustomerClient {

	static final Client client = ClientBuilder.newClient();

	static final WebTarget baseTarget = client.target("http://localhost:8080/DeliveryService/api/");
	static final WebTarget customerTarget = baseTarget.path("customers");
	static final WebTarget restaurantTarget = baseTarget.path("restaurants");
	static final WebTarget menuTarget = baseTarget.path("restaurants/{restaurantId}/menuitems");
	static final WebTarget orderTarget = baseTarget.path("orders");

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		LoginCredentials loginCredentials = null;

		System.out.println("Delivery Service REST API Test Client for Customers");
		int input = 0;
		boolean testing = true;
		while (testing) {
			printMenu();
			input = getUserInputInt(scan);
			switch (input) {
			case 1:
				login(scan, loginCredentials);
				break;

			case 2:
				register(scan);
				break;

			case 3:
				findAndPrintAllRestaurants();
				break;

			case 4:
				Restaurant chosenRestaurant = findAndChooseAvailableRestaurant(scan);
				List<MenuItem> shoppingCart = fillShoppingCart(scan, chosenRestaurant);
				if (loginCredentials != null) {
					String username2 = loginCredentials.getUsername();
					String password2 = loginCredentials.getPassword();
					List<Customer> customers2 = customerTarget.request()
							.header("Authorization",
									encode(loginCredentials.getUsername(), loginCredentials.getPassword()))
							.get(new GenericType<List<Customer>>() {
							});
					Optional<Customer> loggedInCustomer = customers2.stream()
							.filter(customer3 -> customer3.getEmail().equals(username2)
									&& customer3.getPassword().equals(password2))
							.findFirst();
					if (loggedInCustomer.isPresent()) {
						Order order = new Order(false, shoppingCart, chosenRestaurant, loggedInCustomer.get());
						Response postResponse2 = orderTarget.request().post(Entity.json(order));
						System.out.println(postResponse2);
					}
				} else {
					System.out.println("Please create your user account or login:");
					System.out.println("1)Login");
					System.out.println("2)Register");
					System.out.println("3)Cancel");
					int input4 = 0;
					while (input4 <= 0 || input4 >= 4) {
						input4 = getUserInputInt(scan);
					}
					switch (input4) {
					case 1:
						Optional<Customer> loggedInCustomer = login(scan, loginCredentials);
						if (loggedInCustomer.isPresent()) {
							Order order = new Order(false, shoppingCart, chosenRestaurant, loggedInCustomer.get());
							Response postResponse2 = orderTarget.request().post(Entity.json(order));
							System.out.println(postResponse2);
						}
						break;
					case 2:
						Customer customer4 = register(scan);
						Order order = new Order(false, shoppingCart, chosenRestaurant, customer4);
						Response postResponse2 = orderTarget.request().post(Entity.json(order));
						System.out.println(postResponse2);
						break;
					case 3:
						break;
					}
				}
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

	private static List<MenuItem> fillShoppingCart(Scanner scan, Restaurant chosenRestaurant) {
		List<MenuItem> menuItems = menuTarget.resolveTemplate("restaurantId", chosenRestaurant.getId()).request()
				.get(new GenericType<List<MenuItem>>() {
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

				shopping = false;
			}

		}
		;
		return shoppingCart;
	}

	private static Restaurant findAndChooseAvailableRestaurant(Scanner scan) {
		List<Restaurant> restaurants2 = restaurantTarget.request().get(new GenericType<List<Restaurant>>() {
		});
		List<Restaurant> availableRestaurants = restaurants2.stream().filter(restaurant -> restaurant.isAvailable())
				.collect(Collectors.toList());
		System.out.println("Choose a restaurant:");
		for (int i = 0; i < availableRestaurants.size(); i++) {
			System.out.println((i + 1) + ")" + availableRestaurants.get(i));
		}
		int input2 = getUserInputInt(scan);
		Restaurant chosenRestaurant = availableRestaurants.get(input2 - 1);
		return chosenRestaurant;
	}

	private static void findAndPrintAllRestaurants() {
		List<Restaurant> restaurants = restaurantTarget.request().get(new GenericType<List<Restaurant>>() {
		});
		restaurants.stream().forEach(System.out::println);
	}

	private static Optional<Customer> login(Scanner scan, LoginCredentials loginCredentials) {
		loginCredentials = getLoginCredentialsFromUser(scan);
		String username = loginCredentials.getUsername();
		String password = loginCredentials.getPassword();
		List<Customer> customers = customerTarget.request()
				.header("Authorization", encode(loginCredentials.getUsername(), loginCredentials.getPassword()))
				.get(new GenericType<List<Customer>>() {
				});
		Optional<Customer> filteredCustomer = customers.stream()
				.filter(customer -> (customer.getEmail().equals(username) && customer.getPassword().equals(password)))
				.findFirst();
		if (filteredCustomer.isPresent()) {
			System.out.println(filteredCustomer.get());

		} else
			System.out.println("Customer not found.");
		return filteredCustomer;
	}

	private static Customer register(Scanner scan) {
		Customer customer = getCustomerData(scan);
		Response postResponse = customerTarget.request().post(Entity.json(customer));
		System.out.println(postResponse);
		return customer;
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
