package com.supplychainmanagement.OrderResponseDTO;

import com.supplychainmanagement.Entity.Product;


public class OrderProductDTO {
    private int id;
    private String name;
    private double price;
    private int stockquantity;
    private OrderSupplierDTO supplier;

    public OrderProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stockquantity = product.getStockquantity();

        // Convert Supplier manually
        if (product.getSupplier() != null) {
            this.supplier = new OrderSupplierDTO(product.getSupplier());
        }
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

	public OrderSupplierDTO getSupplier() {
		return supplier;
	}

	public void setSupplier(OrderSupplierDTO supplier) {
		this.supplier = supplier;
	}
}
