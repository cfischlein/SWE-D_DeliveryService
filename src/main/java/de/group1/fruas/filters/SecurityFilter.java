package de.group1.fruas.filters;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.codec.binary.Base64;

import de.group1.fruas.database.DatabaseClass;
import de.group1.fruas.model.Customer;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
	private static final String SECURED_URL_PREFIX = "customers";
	private Map<Integer, Customer> customers = DatabaseClass.getCustomers();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Authentication only for GET requests on Customer resource
		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)
				&& requestContext.getMethod().equals("GET")) {
			
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				byte[] decodeBase64 = Base64.decodeBase64(authToken);
				String decodedToken = new String(decodeBase64, "UTF-8");
				StringTokenizer tokenier = new StringTokenizer(decodedToken, ":");
				String username = tokenier.nextToken();
				String password = tokenier.nextToken();

				for (Customer customer : customers.values()) {
					if (customer.getEmail().equals(username) && customer.getPassword().equals(password)) {
						return;
					}
				}
			}
			Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
					.entity("User cannot access this resource.").build();
			requestContext.abortWith(unauthorizedStatus);
		}

	}

}
