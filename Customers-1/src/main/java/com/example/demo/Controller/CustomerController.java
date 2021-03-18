package com.example.demo.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Customer;
import com.example.demo.Service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	
	@Autowired
	private CustomerService service;
	
	
	@PostMapping("/")
	public String saveCustomer(@RequestBody Customer customer) throws Exception {
		return service.saveCustomer(customer);
	}
	
	
	@GetMapping("/")
	public List<Customer> getAllCustomers() throws InterruptedException, ExecutionException{
		return service.getAllCustomers();
	}
	
	@PutMapping("/")
	public String updateCustomer(@RequestBody Customer customer) throws InterruptedException, ExecutionException {
		return service.updateCustomer(customer);
	}
	
	@GetMapping("/{name}")
	public Customer getCustomerByName(@PathVariable String name) throws ExecutionException, Exception {
		return service.getCustomerByName(name);
	}
	
	
	@DeleteMapping("/{name}")
	public String deleteCustomerByName(@PathVariable String name) {
		return service.deleteCutomer(name);
	}

}
