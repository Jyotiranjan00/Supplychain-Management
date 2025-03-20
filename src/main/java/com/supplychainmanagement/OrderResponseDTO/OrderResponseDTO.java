package com.supplychainmanagement.OrderResponseDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.supplychainmanagement.Entity.Order;
import com.supplychainmanagement.Entity.Product;

public class OrderResponseDTO {
    private int id;
    private LocalDate orderdate;
    private double totalamount;
    private String status;
    private long trackingnumber;
    private OrderCustomerDTO customer;
    private List<OrderProductDTO> products;

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.orderdate = order.getOrderdate();
        this.totalamount = order.getTotalamount();
        this.status = order.getStatus();
        this.trackingnumber = order.getTrackingnumber();

        // Convert Customer
        if (order.getCustomer() != null) {
            this.customer = new OrderCustomerDTO(order.getCustomer());
        }

        // Convert Products safely
        this.products = new ArrayList<>();
        if (order.getProducts() != null) {
            for (Product product : order.getProducts()) {
                this.products.add(new OrderProductDTO(product));
            }
        }
    }
 // Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTrackingnumber() {
		return trackingnumber;
	}

	public void setTrackingnumber(long trackingnumber) {
		this.trackingnumber = trackingnumber;
	}

	public OrderCustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(OrderCustomerDTO customer) {
		this.customer = customer;
	}

	public List<OrderProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProductDTO> products) {
		this.products = products;
	}

    
    
    
}
