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

import de.group1.fruas.model.Customer;
import de.group1.fruas.services.CustomerService;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

	CustomerService customerService = new CustomerService();
	
	@GET
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@GET
	@Path("/{customerId}")
	public Customer getCustomer(@PathParam("customerId") int id) {	
		return 	customerService.getCustomer(id);
	}
	
	@POST
	public Customer addCustomer(Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@PUT
	@Path("/{customerId}")
	public Customer updateCustomer(@PathParam("customerId") int id, Customer customer) {
		customer.setId(id);
		return customerService.editCustomer(customer);
	}
	
	@DELETE
	@Path("/{customerId}")
	public Customer deleteCustomer(@PathParam("customerId") int id) {
		return customerService.deleteCustomer(id);
	}
}
