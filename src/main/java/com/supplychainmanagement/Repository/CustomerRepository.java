package com.supplychainmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supplychainmanagement.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	//custom methods and jpa repository methods

}
