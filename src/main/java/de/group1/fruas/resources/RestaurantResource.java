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

import de.group1.fruas.model.Restaurant;
import de.group1.fruas.services.RestaurantService;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource {

RestaurantService restaurantService = new RestaurantService();
	
	@GET	
	public List<Restaurant> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}
	
	@GET
	@Path("/{restaurantId}")
	public Restaurant addRestaurant(@PathParam("restaurantId") int id) {	
		return 	restaurantService.getRestaurant(id);
	}
	
	@POST
	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantService.addRestaurant(restaurant);
	}
	
	@PUT
	@Path("/{restaurantId}")
	public Restaurant updateRestaurant(@PathParam("restaurantId") int id, Restaurant restaurant) {
		restaurant.setId(id);
		return restaurantService.editRestaurant(restaurant);
	}
	
	@DELETE
	@Path("/{restaurantId}")
	public Restaurant deleteRestaurant(@PathParam("restaurantId") int id) {
		return restaurantService.deleteRestaurant(id);
	}
	
	@Path("/{restaurantId}/menuitems")
	public MenuItemResource getMenuItemResource() {
		return new MenuItemResource();
	}
	
}
