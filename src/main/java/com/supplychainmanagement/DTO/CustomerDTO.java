package com.supplychainmanagement.DTO;

import com.supplychainmanagement.Entity.Customer;

public class CustomerDTO {
	
	private int id;
    private String name;
    private String email;
    private long phone;
    private String address;
    
    public CustomerDTO() {}
    
    public CustomerDTO(int id, String name, String email, long phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
    
	

    public CustomerDTO(Customer updatedCustomer) {
        this.id = updatedCustomer.getId();
        this.name = updatedCustomer.getName();
        this.email = updatedCustomer.getEmail();
        this.phone = updatedCustomer.getPhone();
        this.address = updatedCustomer.getAddress();
    }




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
