package de.group1.fruas.model;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Restaurant {
	private int id;
	private String name;
	private String password;
	private String email;
	private Address address;
	private Membership membership;
	private Map<Integer, Feedback> feedback = new HashMap<>();
	private Map<Integer, MenuItem> menuItems = new HashMap<>();
	private boolean isAvailable;

	public Restaurant() {

	}

	public Restaurant(int id, String name, String password, String email, Address address, Membership membership,
			HashMap<Integer, Feedback> feedback, HashMap<Integer, MenuItem> menuItems, boolean isAvailable) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.membership = membership;
		this.feedback = feedback;
		this.menuItems = menuItems;
		this.isAvailable = isAvailable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@XmlTransient
	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public Map<Integer, Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(Map<Integer, Feedback> feedback) {
		this.feedback = feedback;
	}

	public Map<Integer, MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Map<Integer, MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", address="
				+ address + ", membership=" + membership + ", feedback=" + feedback + ", menuItems=" + menuItems + "]";
	}

}
