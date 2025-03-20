package com.supplychainmanagement.DTO;

import com.supplychainmanagement.Entity.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class ProductDTO {

	private int id;
    private String name;
    private double price;
    private int stockquantity;
    private int supplier_id;

    

	public ProductDTO(Product product) {
		// TODO Auto-generated constructor stub
		this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stockquantity = product.getStockquantity();
        this.supplier_id=product.getSupplier().getId() ;//(product.getSupplier() != null) ? product.getSupplier().getId() : 0;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockquantity() {
		return stockquantity;
	}

	public void setStockquantity(int stockquantity) {
		this.stockquantity = stockquantity;
	}

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", price=" + price + ", stockquantity=" + stockquantity
				+ ", supplier_id=" + supplier_id + "]";
	}

}
