package com.supplychainmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supplychainmanagement.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	//custom methods and jpa repository methods
	
	List<Order> findByCustomerId(int customerId);
	
	Order findByTrackingnumber(long trackingNumber);
	

}
