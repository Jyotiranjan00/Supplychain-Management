package com.supplychainmanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supplychainmanagement.Entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	
	//custom methods and jpa repository methods
	
	
	

}
