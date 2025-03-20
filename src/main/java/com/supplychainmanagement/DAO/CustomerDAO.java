package com.supplychainmanagement.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.supplychainmanagement.Entity.Customer;
import com.supplychainmanagement.Repository.CustomerRepository;
@Repository
public class CustomerDAO {
	
	@Autowired
	private CustomerRepository customerRepository;

	public Customer saveCustomerDAO(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	public Optional<Customer> getCustomerInfoByIdDAO(int id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id);
	}

	public List<Customer> getAllCustomerInfoDAO() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	

	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	public void deleteCustomerInfo(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.delete(customer);
		
	}

	public Optional<Customer> getCustomerById(int id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id);
	}

	

	
}
