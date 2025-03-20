package com.supplychainmanagement.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.supplychainmanagement.DAO.CustomerDAO;
import com.supplychainmanagement.DAO.OrderDAO;
import com.supplychainmanagement.DAO.ProductDAO;
import com.supplychainmanagement.DTO.OrderDTO;
import com.supplychainmanagement.DTO.ResponseStructure;
import com.supplychainmanagement.Entity.Customer;
import com.supplychainmanagement.Entity.Order;
import com.supplychainmanagement.Entity.Product;
import com.supplychainmanagement.Entity.Supplier;
import com.supplychainmanagement.OrderResponseDTO.OrderBasicDetailsDTO;
import com.supplychainmanagement.OrderResponseDTO.OrderResponseDTO;
import com.supplychainmanagement.OrderResponseDTO.UpdateOrderDTO;
@Service
public class OrderService {
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private ProductDAO productDAO; // Inject ProductDAO to fetch products
	
	//create_order
	 public ResponseEntity<ResponseStructure<Order>> createOrder(OrderDTO orderDTO) {
	        ResponseStructure<Order> responseStructure = new ResponseStructure<>();
	        Order order = new Order();

	        // Fetch Customer
	        Optional<Customer> customerOpt = customerDAO.getCustomerById(orderDTO.getCustomerId());
	        if (!customerOpt.isPresent()) {
	            responseStructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
	            responseStructure.setMessage("Invalid Customer ID: " + orderDTO.getCustomerId());
	            return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
	        }
	        order.setCustomer(customerOpt.get());

	        // Fetch Products
	        List<Product> products = productDAO.getProductsByIds(orderDTO.getProductIds());
	        if (products.isEmpty()) {
	            responseStructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
	            responseStructure.setMessage("Invalid Product IDs provided");
	            return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
	        }

	        // Set Order reference in each Product
	        for (Product product : products) {
	            product.setOrder(order);
	        }

	        // Set Order Details
	        order.setOrderdate(orderDTO.getOrderDate());
	        order.setTotalamount(orderDTO.getTotalAmount());
	        order.setStatus(orderDTO.getStatus());
	        order.setTrackingnumber(orderDTO.getTrackingNumber());
	        order.setProducts(products);

	        // Save Order
	        Order savedOrder = orderDAO.saveOrder(order);

	        // Save Products separately to ensure `order_product` is populated
	        productDAO.saveAllProducts(products);  // Ensure products are saved after setting order

	        // Set Response
	        responseStructure.setStatuscode(HttpStatus.CREATED.value());
	        responseStructure.setMessage("Order created successfully.");
	        responseStructure.setData(savedOrder);

	        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	    }
	 
	 //get_All_Order
	public ResponseEntity<ResponseStructure<List<OrderResponseDTO>>> getAllOrdersDetails() {
		List<Order> orders = orderDAO.getAllOrders();
        List<OrderResponseDTO> orderResponseDTOs = new ArrayList<>();

        // Manually converting Order entities to OrderResponseDTO
        for (Order order : orders) {
            orderResponseDTOs.add(new OrderResponseDTO(order));
        }

        // Creating the response structure
        ResponseStructure<List<OrderResponseDTO>> responseStructure = new ResponseStructure<>();
        responseStructure.setStatuscode(HttpStatus.OK.value());
        responseStructure.setMessage("Orders fetched successfully.");
        responseStructure.setData(orderResponseDTOs);

        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		
	}

	public ResponseEntity<ResponseStructure<Order>> getOrderByIdService(int id) {
		Optional<Order> opt=orderDAO.getOrderDetailsById(id);
		ResponseStructure<Order> structure=new ResponseStructure<>();
		if(opt.isPresent()) {
			structure.setStatuscode(HttpStatus.FOUND.value());
			structure.setMessage("Found");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Order>>(structure,HttpStatus.FOUND);
		}
		else {
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found by given id:- "+id);
			return new ResponseEntity<ResponseStructure<Order>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<OrderBasicDetailsDTO>>> getOrdersByCustomerId(int customerId) {
        ResponseStructure<List<OrderBasicDetailsDTO>> responseStructure = new ResponseStructure<>();
        
        List<Order> orders = orderDAO.findOrdersByCustomerId(customerId);
        
        // Convert Order entities to OrderBasicDTO
        List<OrderBasicDetailsDTO> orderBasicDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderBasicDTOs.add(new OrderBasicDetailsDTO(order));
        }

        if (orderBasicDTOs.isEmpty()) {
            responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("No orders found for customer ID: " + customerId);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        else {
        	responseStructure.setStatuscode(HttpStatus.OK.value());
            responseStructure.setMessage("Orders fetched successfully.");
            responseStructure.setData(orderBasicDTOs);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
        
    }

	public ResponseEntity<ResponseStructure<OrderBasicDetailsDTO>> getOrderByTrackingNumber(long trackingNumber) {
		
		ResponseStructure<OrderBasicDetailsDTO> responseStructure = new ResponseStructure<>();

        Order order = orderDAO.findOrderByTrackingNumber(trackingNumber);

        if (order == null) {
            responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("No order found for tracking number: " + trackingNumber);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        else {
        	 OrderBasicDetailsDTO orderBasicDTO = new OrderBasicDetailsDTO(order);

             responseStructure.setStatuscode(HttpStatus.OK.value());
             responseStructure.setMessage("Order fetched successfully.");
             responseStructure.setData(orderBasicDTO);
             return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
       
	}

	public ResponseEntity<ResponseStructure<Order>> updateOrderInfoService(UpdateOrderDTO updateOrderDTO) {
	    ResponseStructure<Order> structure = new ResponseStructure<>();

	    if (updateOrderDTO.getId() == 0) {
	        structure.setStatuscode(HttpStatus.BAD_REQUEST.value());
	        structure.setMessage("Order ID is required for update");
	        structure.setData(null);
	        return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	    }

	    Optional<Order> existingOrderOpt = orderDAO.getOrderInfoByIdDAO(updateOrderDTO.getId());

	    if (existingOrderOpt.isPresent()) {
	        Order existingOrder = existingOrderOpt.get();

	        // Convert DTO to Order (Only update provided fields)
	        if (updateOrderDTO.getOrderdate() != null) {
	            existingOrder.setOrderdate(updateOrderDTO.getOrderdate());
	        }
	        if (updateOrderDTO.getTotalamount() != null) {
	            existingOrder.setTotalamount(updateOrderDTO.getTotalamount());
	        }
	        if (updateOrderDTO.getStatus() != null) {
	            existingOrder.setStatus(updateOrderDTO.getStatus());
	        }
	        if (updateOrderDTO.getTrackingnumber() != null) {
	            existingOrder.setTrackingnumber(updateOrderDTO.getTrackingnumber());
	        }

	        // Save the updated order
	        Order updatedOrder = orderDAO.updateOrder(existingOrder);

	        structure.setStatuscode(HttpStatus.OK.value());
	        structure.setMessage("Order updated successfully");
	        structure.setData(updatedOrder);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatuscode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Order not found with ID: " + updateOrderDTO.getId());
	        structure.setData(null);
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}
	
	public ResponseEntity<ResponseStructure<Order>> deleteOrderService(int orderId) {
	    ResponseStructure<Order> structure = new ResponseStructure<>();
	    
	    Optional<Order> existingOrderOpt = orderDAO.getOrderInfoByIdDAO(orderId);

	    if (existingOrderOpt.isPresent()) {
	        Order existingOrder = existingOrderOpt.get();
	        orderDAO.delete(existingOrder); // Call DAO delete() method

	        structure.setStatuscode(HttpStatus.OK.value());
	        structure.setMessage("Order deleted successfully");
	        structure.setData(existingOrder); // Sending back deleted order details

	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatuscode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Order not found with ID: " + orderId);
	        structure.setData(null);

	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}



	

	
	


}
