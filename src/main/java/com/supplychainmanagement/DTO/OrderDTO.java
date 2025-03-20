package com.supplychainmanagement.DTO;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private int id;
    private LocalDate orderDate;
    private double totalAmount;
    private String status;
    private long trackingNumber;
    private int customerId;
    private List<Integer> productIds;

    // Constructors
    public OrderDTO() {}

    public OrderDTO(int id, LocalDate orderDate, double totalAmount, String status, long trackingNumber, int customerId, List<Integer> productIds) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.trackingNumber = trackingNumber;
        this.customerId = customerId;
        this.productIds = productIds;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public long getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(long trackingNumber) { this.trackingNumber = trackingNumber; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public List<Integer> getProductIds() { return productIds; }
    public void setProductIds(List<Integer> productIds) { this.productIds = productIds; }
}
