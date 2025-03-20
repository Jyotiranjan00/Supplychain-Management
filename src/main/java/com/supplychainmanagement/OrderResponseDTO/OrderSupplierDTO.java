package com.supplychainmanagement.OrderResponseDTO;

import com.supplychainmanagement.Entity.Supplier;


public class OrderSupplierDTO {
    private int  id;
    private String name;
    private long contact;
    private String email;
    private String companyname;

    public OrderSupplierDTO(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.contact = supplier.getContact();
        this.email = supplier.getEmail();
        this.companyname = supplier.getCompanyname();
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

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
}
