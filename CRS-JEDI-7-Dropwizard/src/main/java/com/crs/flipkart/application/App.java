package com.crs.flipkart.application;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
 
import org.apache.log4j.Logger;

import com.crs.flipkart.restController.AdminRestAPI;
import com.crs.flipkart.restController.ProfessorRestAPI;
import com.crs.flipkart.restController.StudentRestAPI;
import com.crs.flipkart.restController.UserRestAPI;
 
 
public class App extends Application<Configuration> {
	private static final Logger logger = Logger.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }
 
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        logger.info("Registering REST resources");
        e.jersey().register(new UserRestAPI());
        e.jersey().register(new StudentRestAPI());
        e.jersey().register(new AdminRestAPI());
        e.jersey().register(new ProfessorRestAPI());
    }
 
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}