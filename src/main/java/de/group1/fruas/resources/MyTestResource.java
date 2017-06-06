package de.group1.fruas.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class MyTestResource {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String testMethod() {
		return "it worked";	
	}
}
