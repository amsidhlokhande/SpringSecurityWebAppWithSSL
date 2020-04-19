package com.amsidh.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@GetMapping("/{id}")
	public Customer GetCustomer(@PathVariable Long id) {

		LOGGER.info(String.format("Customer with customer is %d and name %s", id, "Cusotmer " + id));
		return new Customer(id, "Customer" + id);
	}
}
