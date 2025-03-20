package com.supplychainmanagement.OrderResponseDTO;

import java.time.LocalDate;

import com.supplychainmanagement.Entity.Customer;
import com.supplychainmanagement.Entity.Order;

public class OrderBasicDetailsDTO {
    private int id;
    private LocalDate orderdate;
    private double totalamount;
    private String status;
    private long trackingnumber;
    private Customer customer_id;

    public OrderBasicDetailsDTO(Order order) {
        this.id = order.getId();
        this.orderdate = order.getOrderdate();
        this.totalamount = order.getTotalamount();
        this.status = order.getStatus();
        this.trackingnumber = order.getTrackingnumber();
        this.customer_id=order.getCustomer();
    }

    // Getters & Setters
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

	public Customer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Customer customer_id) {
		this.customer_id = customer_id;
	}
}
