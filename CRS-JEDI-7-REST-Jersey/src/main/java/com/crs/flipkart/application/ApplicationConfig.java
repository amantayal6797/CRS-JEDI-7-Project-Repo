/**
 * 
 */
package com.crs.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.crs.flipkart.restcontroller.AdminRestAPI;
import com.crs.flipkart.restcontroller.CustomerController;

/**
 * @author Nitish
 *
 */
public class ApplicationConfig extends ResourceConfig {
	
	public ApplicationConfig() {
		register(CustomerController.class);
		register(AdminRestAPI.class);
	}

}