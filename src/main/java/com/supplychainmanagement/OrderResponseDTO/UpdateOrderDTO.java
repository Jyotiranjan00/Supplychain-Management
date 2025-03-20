package com.supplychainmanagement.OrderResponseDTO;

import java.time.LocalDate;
import java.util.Date;

public class UpdateOrderDTO {
    private int id; // Order ID (Required)
    private LocalDate orderdate;
    private Double totalamount;
    private String status;
    private Long trackingnumber;
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
	public Double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getTrackingnumber() {
		return trackingnumber;
	}
	public void setTrackingnumber(Long trackingnumber) {
		this.trackingnumber = trackingnumber;
	}

    
}
