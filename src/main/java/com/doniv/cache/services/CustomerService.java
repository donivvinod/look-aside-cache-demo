package com.doniv.cache.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.doniv.cache.entities.Customer;
import com.doniv.cache.repositories.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@CacheEvict(cacheNames = {"customersCache"}, allEntries = true)
	public Customer addCustomer(Customer customer) {
		
		log.info("Adding Customer Data to the db {}, {}", customer.getName() , customer.getEmail());
		return customerRepository.save(customer);
	}
	
	@CacheEvict(cacheNames = {"customersCache"}, allEntries = true)
	public Customer updateCustomer(Customer customer) {
		
		log.info("Updating Customer Data to the db {} -> {}, {}", customer.getId(), customer.getName() , customer.getEmail());
		return customerRepository.save(customer);
	}

	@Cacheable(cacheNames = "customersCache")
	public List<Customer> getCustomers() {
		log.info("Fetching customers from the db");
		try {
			Thread.sleep(5000); //Sleep for 5 seconds to slow down..
		} catch (InterruptedException e) {
			log.error("An error occured --> {}", e.getMessage());
		} 
		return (List<Customer>) customerRepository.findAll();
	}
	
	@Cacheable(cacheNames = "customersCache",key = "#id")
	public Optional<Customer> getCustomer(long id) {
		log.info("Fetching customers from the db");
		return customerRepository.findById(id);
	}
	
	
	@CacheEvict(cacheNames = {"customersCache"}, allEntries = true)
	public void evictAll() {
		log.info("Evicting all customers cache");
	}
	
	
}
