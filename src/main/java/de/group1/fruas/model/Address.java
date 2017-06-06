package de.group1.fruas.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address {

	private String postalCode;
	private String city;
	private String street;
	private String houseNumber;
	
	public Address() {
		
	}

	public Address(String postalCode, String city, String street, String houseNumber) {
		this.postalCode = postalCode;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	@Override
	public String toString() {
		return "Address [postalCode=" + postalCode + ", city=" + city + ", street=" + street + ", houseNumber="
				+ houseNumber + "]";
	}
	
	

}
