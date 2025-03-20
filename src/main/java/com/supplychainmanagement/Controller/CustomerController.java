package com.supplychainmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychainmanagement.DTO.CustomerDTO;
import com.supplychainmanagement.DTO.ResponseStructure;
import com.supplychainmanagement.Entity.Customer;
import com.supplychainmanagement.Service.CustomerService;

@RestController
@RequestMapping("/jsp/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	//addCustomer
//	@PostMapping
//	public ResponseEntity<ResponseStructure<Customer>> saveSuplier(@RequestBody Customer customer){
//		
//		return customerService.saveCustomerInfoService(customer);
//	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<CustomerDTO>> saveCustomer(@RequestBody CustomerDTO customerDTO) {
	    return customerService.saveCustomerInfoService(customerDTO);
	}

	
	//getCustomerById
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Customer>> getByCustomerID(@PathVariable int id){
		
		return customerService.getCustomerInfoByIdService(id);
	}
	
	//getAllCustomer
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
		return customerService.getAllCustomerInfoService();
	}
	
	//updateCustomer
//	@PutMapping
//    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer) {
//        return customerService.updateCustomerInfoService(customer);
//    }
	@PutMapping
	public ResponseEntity<ResponseStructure<CustomerDTO>> updateCustomer(@RequestBody CustomerDTO customerDTO) {
	    return customerService.updateCustomerInfoService(customerDTO);
	}

	
	//deleteCustomer
	@DeleteMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@PathVariable int id){
		
		return customerService.deleteCustomerInfoService(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
