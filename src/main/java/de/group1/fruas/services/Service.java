package de.group1.fruas.services;

import java.util.List;



public interface Service<T> {

	public List<T> getAllItems();
	public T addItem(T type);
	public T deleteItem(int itemId);
	public T editItem(T type);
	public T getItem(int itemId);
	
}
