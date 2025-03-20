package com.supplychainmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.supplychainmanagement.DAO.CustomerDAO;
import com.supplychainmanagement.DTO.CustomerDTO;
import com.supplychainmanagement.DTO.ResponseStructure;
import com.supplychainmanagement.Entity.Customer;
@Service
public class CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;

//	public ResponseEntity<ResponseStructure<Customer>> saveCustomerInfoService(Customer customer) {
//		Customer cus=customerDAO.saveCustomerDAO(customer);
//		ResponseStructure<Customer> structure= new ResponseStructure<Customer>();
//		structure.setStatuscode(HttpStatus.CREATED.value());
//		structure.setMessage("created");
//		structure.setData(cus);
//		return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);
//	}
	
	public ResponseEntity<ResponseStructure<CustomerDTO>> saveCustomerInfoService(CustomerDTO customerDTO) {
	    // Convert DTO to Entity
	    Customer customer = new Customer();
	    customer.setName(customerDTO.getName());
	    customer.setEmail(customerDTO.getEmail());
	    customer.setPhone(customerDTO.getPhone());
	    customer.setAddress(customerDTO.getAddress());

	    // Save Customer
	    Customer savedCustomer = customerDAO.saveCustomerDAO(customer);

	    // Convert Entity back to DTO for response
	    CustomerDTO responseDTO = new CustomerDTO(
	        savedCustomer.getId(),
	        savedCustomer.getName(),
	        savedCustomer.getEmail(),
	        savedCustomer.getPhone(),
	        savedCustomer.getAddress()
	    );

	    // Prepare Response
	    ResponseStructure<CustomerDTO> structure = new ResponseStructure<>();
	    structure.setStatuscode(HttpStatus.CREATED.value());
	    structure.setMessage("Customer created successfully");
	    structure.setData(responseDTO);

	    return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	
	public ResponseEntity<ResponseStructure<Customer>> getCustomerInfoByIdService(int id) {
		Optional<Customer> opt=customerDAO.getCustomerInfoByIdDAO(id);
		
		ResponseStructure<Customer> structure= new ResponseStructure<Customer>();
		if(opt.isPresent()) {
			structure.setStatuscode(HttpStatus.FOUND.value());
			structure.setMessage("found");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.FOUND);
		}
		else {
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("can not find the info of given id: "+id);
			return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomerInfoService() {
		// TODO Auto-generated method stub
		List<Customer> customers=customerDAO.getAllCustomerInfoDAO();
		ResponseStructure<List<Customer>> structure=new ResponseStructure<List<Customer>>();
		if(!customers.isEmpty()) {
			structure.setStatuscode(HttpStatus.FOUND.value());
			structure.setMessage("found");
			structure.setData(customers);
			return new ResponseEntity<ResponseStructure<List<Customer>>>(structure,HttpStatus.FOUND);
		}
		else {
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("not found");
			return new ResponseEntity<ResponseStructure<List<Customer>>>(structure,HttpStatus.NOT_FOUND);
		}
		
	}

//	public ResponseEntity<ResponseStructure<Customer>> updateCustomerInfoService(Customer customer) {
//		// TODO Auto-generated method stub
//		Optional<Customer> customers = customerDAO.getCustomerInfoByIdDAO(customer.getId());
//	    ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
//
//	    if (customers.isPresent()) {
//	        Customer updatedCustomer = customerDAO.updateCustomer(customer); 
//	        structure.setStatuscode(HttpStatus.OK.value());
//	        structure.setMessage("The record is updated successfully");
//	        structure.setData(updatedCustomer);
//
//	        return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
//	    } 
//	    else {
//	        structure.setStatuscode(HttpStatus.NOT_FOUND.value());
//	        structure.setMessage("Customer not found with ID: " + customer.getId());
//	        structure.setData(null);
//	        return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.NOT_FOUND);
//	    }
//
//		
//	}
	//2ndtry
//	public ResponseEntity<ResponseStructure<Customer>> updateCustomerInfoService(Customer customer) {
//	    Optional<Customer> customers = customerDAO.getCustomerInfoByIdDAO(customer.getId());
//	    ResponseStructure<Customer> structure = new ResponseStructure<>();
//
//	    if (customers.isPresent()) {
//	        Customer updatedCustomer = customerDAO.updateCustomer(customer); 
//	        structure.setStatuscode(HttpStatus.OK.value());
//	        structure.setMessage("The record is updated successfully");
//	        structure.setData(new CustomerDTO(updatedCustomer)); // Return DTO instead of full entity
//
//	        return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
//	    } else {
//	        structure.setStatuscode(HttpStatus.NOT_FOUND.value());
//	        structure.setMessage("Customer not found with ID: " + customer.getId());
//	        structure.setData(null);
//	        return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.NOT_FOUND);
//	    }
//	}

	public ResponseEntity<ResponseStructure<Customer>> deleteCustomerInfoService(int id) {
		// TODO Auto-generated method stub
		Optional<Customer> opt=customerDAO.getCustomerInfoByIdDAO(id);//it automatically 1 no findById()
		ResponseStructure<Customer> structure= new ResponseStructure<Customer>();
		if(opt.isPresent()) {
			customerDAO.deleteCustomerInfo(opt.get());
			structure.setStatuscode(HttpStatus.OK.value());
			structure.setMessage("the record is deleted");
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
		}
		else {
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Customer not found with ID: " + id);
	        structure.setData(null);
	        return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.NOT_FOUND);
			
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDTO>> updateCustomerInfoService(CustomerDTO customerDTO) {
		Optional<Customer> customerOptional = customerDAO.getCustomerInfoByIdDAO(customerDTO.getId());
	    ResponseStructure<CustomerDTO> structure = new ResponseStructure<>();

	    if (customerOptional.isPresent()) {
	        Customer customer = customerOptional.get();
	        customer.setName(customerDTO.getName());
	        customer.setEmail(customerDTO.getEmail());
	        customer.setPhone(customerDTO.getPhone());
	        customer.setAddress(customerDTO.getAddress());

	        Customer updatedCustomer = customerDAO.updateCustomer(customer); 
	        structure.setStatuscode(HttpStatus.OK.value());
	        structure.setMessage("The record is updated successfully");
	        structure.setData(new CustomerDTO(updatedCustomer)); 

	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatuscode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Customer not found with ID: " + customerDTO.getId());
	        structure.setData(null);
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
		
	}

}
