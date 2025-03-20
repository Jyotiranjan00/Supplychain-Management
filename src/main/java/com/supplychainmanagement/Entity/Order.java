package com.supplychainmanagement.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate orderdate;
	private double totalamount;
	private String status;
	private long trackingnumber;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<Product> products;
	
	
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	@JsonManagedReference
	private Customer customer;


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


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", orderdate=" + orderdate + ", totalamount=" + totalamount + ", status=" + status
				+ ", trackingnumber=" + trackingnumber + ", products=" + products + ", customer=" + customer + "]";
	}


	

}
