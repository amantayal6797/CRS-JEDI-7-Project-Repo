/**
 * 
 */
package com.crs.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.crs.flipkart.restController.CustomerController;

/**
 * @author aditya
 *
 */
public class ApplicationConfig extends ResourceConfig {
	public ApplicationConfig() {
		register(CustomerController.class);
	}
}
