package de.group1.fruas.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.group1.fruas.model.Customer;
import de.group1.fruas.services.CustomerService;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

	CustomerService customerService = new CustomerService();
	
	@GET
	public List<Customer> getAllCustomers() {
		return customerService.getAllItems();
	}
	
	@GET
	@Path("/{customerId}")
	public Customer getCustomer(@PathParam("customerId") int id) {	
		return 	customerService.getItem(id);
	}
	
	@POST
	public Response addCustomer(Customer customer, @Context UriInfo uriInfo) {
		Customer newCustomer = customerService.addItem(customer);
		String newId = String.valueOf(newCustomer.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newCustomer)				
				.build();
	}
	
	@PUT
	@Path("/{customerId}")
	public Customer updateCustomer(@PathParam("customerId") int id, Customer customer) {
		customer.setId(id);
		return customerService.editItem(customer);
	}
	
	@DELETE
	@Path("/{customerId}")
	public Customer deleteCustomer(@PathParam("customerId") int id) {
		return customerService.deleteItem(id);
	}
}
