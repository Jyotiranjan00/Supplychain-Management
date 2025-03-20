package com.supplychainmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supplychainmanagement.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	//custom methods and jpa repository methods
	
	List<Product> findBySupplierId(int supplier_id);

}
