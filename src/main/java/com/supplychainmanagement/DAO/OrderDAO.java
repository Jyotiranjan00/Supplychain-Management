package com.supplychainmanagement.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.supplychainmanagement.DTO.OrderDTO;
import com.supplychainmanagement.Entity.Customer;
import com.supplychainmanagement.Entity.Order;
import com.supplychainmanagement.Entity.Product;
import com.supplychainmanagement.Repository.CustomerRepository;
import com.supplychainmanagement.Repository.OrderRepository;
import com.supplychainmanagement.Repository.ProductRepository;

import jakarta.transaction.Transactional;
@Repository
public class OrderDAO {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
    private ProductRepository productRepository;

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	public Optional<Customer> getCustomerById(int customerId) {
        return customerRepository.findById(customerId);
    }

    // Fetch multiple products by their IDs
    public List<Product> getProductsByIds(List<Integer> productIds) {
        return productRepository.findAllById(productIds);
    }
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}
	
	public Optional<Order> getOrderDetailsById(int id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id);
	}
	public List<Order> findOrdersByCustomerId(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
	public Order findOrderByTrackingNumber(long trackingNumber) {
		return orderRepository.findByTrackingnumber(trackingNumber);
	}
	public Optional<Order> getOrderInfoByIdDAO(int orderId) {
	    return orderRepository.findById(orderId);
	}

	public Order updateOrder(Order order) {
	    return orderRepository.save(order); // Save updated order
	}
	public void delete(Order order) {
	    orderRepository.delete(order);
	}

	    


}
