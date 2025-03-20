package com.supplychainmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychainmanagement.DTO.OrderDTO;
import com.supplychainmanagement.DTO.ResponseStructure;
import com.supplychainmanagement.Entity.Customer;
import com.supplychainmanagement.Entity.Order;
import com.supplychainmanagement.OrderResponseDTO.OrderBasicDetailsDTO;
import com.supplychainmanagement.OrderResponseDTO.OrderResponseDTO;
import com.supplychainmanagement.OrderResponseDTO.UpdateOrderDTO;
import com.supplychainmanagement.Service.OrderService;

@RestController
@RequestMapping("/jsp/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//create order
	@PostMapping
    public ResponseEntity<ResponseStructure<Order>> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }
	
	//get all order
	@GetMapping
	public ResponseEntity<ResponseStructure<List<OrderResponseDTO>>> getAllOrders() {
        return orderService.getAllOrdersDetails();
    }
	
	//getOrderById
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Order>> getOrderById(@PathVariable int id){
		return orderService.getOrderByIdService(id);
	}
	
	//getOrderByCustomerId
	@GetMapping("/id/customer/{customerId}")
	public ResponseEntity<ResponseStructure<List<OrderBasicDetailsDTO>>> getOrdersByCustomerId(@PathVariable int customerId) {
	    return orderService.getOrdersByCustomerId(customerId);
	}
	
	//getOrderByTrackingNumber
	@GetMapping("/tracking/{trackingNumber}")
	public ResponseEntity<ResponseStructure<OrderBasicDetailsDTO>> getOrderByTrackingNumber(@PathVariable long trackingNumber) {
	    return orderService.getOrderByTrackingNumber(trackingNumber);
	}
	
	//update order
	@PutMapping
	public ResponseEntity<ResponseStructure<Order>> updateOrder(@RequestBody UpdateOrderDTO updateOrderDTO) {
	    return orderService.updateOrderInfoService(updateOrderDTO);
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Order>> deleteOrder(@PathVariable int id) { 
	    return orderService.deleteOrderService(id);
	}




	
	

}
