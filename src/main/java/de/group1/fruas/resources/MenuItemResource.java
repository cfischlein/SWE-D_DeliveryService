package de.group1.fruas.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.group1.fruas.model.MenuItem;
import de.group1.fruas.services.MenuItemService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuItemResource {

	private MenuItemService menuItemService = new MenuItemService();

	@GET
	public List<MenuItem> getAllMenuItems(@PathParam("restaurantId") int restaurantId) {
		return menuItemService.getAllMenuItems(restaurantId);
	}
	
	@GET
	@Path("/{menuItemId}")
	public MenuItem getMenuItem(@PathParam("restaurantId") int restaurantId, @PathParam("menuItemId") int menuItemId) {
		return menuItemService.getMenuItem(restaurantId, menuItemId);
	}
	
	@POST
	public MenuItem addMenuItem(@PathParam("restaurantId") int restaurantId, MenuItem menuItem) {
		return menuItemService.addMenuItem(restaurantId, menuItem);
	}
	
	@PUT
	@Path("/{menuItemId}")
	public MenuItem updateMenuItem(@PathParam("restaurantId") int restaurantId, @PathParam("menuItemId") int menuItemId, MenuItem menuItem) {
		menuItem.setId(menuItemId);
		return menuItemService.updateMenuItem(restaurantId, menuItem);
	}
	
	@DELETE
	@Path("/{menuItemId}")
	public MenuItem deleteMenuItem(@PathParam("restaurantId") int restaurantId, @PathParam("menuItemId") int menuItemId) {
		return menuItemService.deleteMenuItem(restaurantId, menuItemId);
	}
	
}
