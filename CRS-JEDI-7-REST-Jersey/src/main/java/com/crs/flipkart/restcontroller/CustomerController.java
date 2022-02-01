/**
 * 
 */
package com.crs.flipkart.restcontroller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import com.crs.flipkart.bean.Customer;

/**
 * @author Nitish
 *
 */
@Path("/CustomerAPI")
public class CustomerController {

	
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerDetails() {
		
		Customer cust = new Customer();
		cust.setId(101);
		cust.setName("Nitish");
		cust.setAddress("Chandigarh");
		
		return cust;
	}
	
	@POST
	@Path("/post")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTrackInJson(Customer cust) {
		
		System.out.println("Hit post service");
		System.out.println("ID: "+cust.getId());
		System.out.println("Name: "+cust.getName());
		
		String result = "Track saved: " + cust;
		return Response.status(201).entity(result).build();
	}
	
	@DELETE
	@Path("/delete/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") int customerId) throws URIReferenceException{
		
		return Response.status(200).entity("Track id " +customerId + "successfully deleted").build();
	}
	
	@PUT
	@Path("update")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer updateCustomer(Customer cust) {
		
		cust.setName(cust.getName() + "updated");
		return cust;
	}
}