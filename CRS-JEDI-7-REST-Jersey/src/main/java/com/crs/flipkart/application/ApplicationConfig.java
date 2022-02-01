/**
 * 
 */
package com.crs.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.crs.flipkart.restController.AdminRestAPI;
import com.crs.flipkart.restController.CustomerController;
import com.crs.flipkart.restController.ProfessorRestAPI;
import com.crs.flipkart.restController.StudentRestAPI;
import com.crs.flipkart.restController.UserRestAPI;

/**
 * @author Nitish
 *
 */
public class ApplicationConfig extends ResourceConfig {
	
	public ApplicationConfig() {
		register(CustomerController.class);
		register(UserRestAPI.class);
		register(StudentRestAPI.class);
		register(ProfessorRestAPI.class);
		register(AdminRestAPI.class);
		
	}

}