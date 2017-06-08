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

import de.group1.fruas.model.Order;
import de.group1.fruas.services.OrderService;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

OrderService orderService = new OrderService();
	
	@GET
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@GET
	@Path("/{orderId}")
	public Order getOrder(@PathParam("orderId") int id) {	
		return 	orderService.getOrder(id);
	}
	
	@POST
	public Order addOrder(Order order) {
		return orderService.addOrder(order);
	}
	
	@PUT
	@Path("/{orderId}")
	public Order updateOrder(@PathParam("orderId") int id, Order order) {
		order.setId(id);
		return orderService.editOrder(order);
	}
	
	@DELETE
	@Path("/{orderId}")
	public Order deleteOrder(@PathParam("orderId") int id) {
		return orderService.deleteOrder(id);
	}
}
