package com.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class with main method to boot the application.
 * 
 * @author Kaushal
 * @version 1.0
 * @since 26-06-2018
 *
 */
@SpringBootApplication
public class StoreApplication {

	private static final Logger logger = LoggerFactory.getLogger(StoreApplication.class);

	/**
	 * This is main method to start spring Boot application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Booting Application..");
		SpringApplication.run(StoreApplication.class, args);
	}
}
