package com.supplychainmanagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychainmanagement.DAO.SupplierDAO;
import com.supplychainmanagement.DTO.ResponseStructure;
import com.supplychainmanagement.Entity.Supplier;
import com.supplychainmanagement.Service.SupplierService;

@RestController
@RequestMapping("/jsp/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
//	@Autowired
//	private SupplierDAO supplierDAO;
	
	//apis and endpoint
	//save supplier
	@PostMapping
	public ResponseEntity<ResponseStructure<Supplier>> saveSuplier(@RequestBody Supplier supplier){
		
		return supplierService.saveSupplierInfoService(supplier);
	}
	
	//getSupplierById
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> getBySupplierID(@PathVariable int id){
		
		return supplierService.getSupplierInfoByIdService(id);
	}
	
	//getAllSupplier
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplier(){
		
		return supplierService.getAllSupplierInfoService();
	}
	
	//updateSupplier
	@PutMapping
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(@RequestBody Supplier supplier){
		
		return supplierService.updateSupplierInfoService(supplier);
	}
	
	//deleteSupplier
	@DeleteMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> deleteSupplier(@PathVariable int id){
		
		return supplierService.deleteSupplierInfoService(id);
	}
	//to only check
//	@GetMapping("/supplier/{id}")
//	public ResponseEntity<Supplier> getSupplierById(@PathVariable int id) {
//	    Optional<Supplier> supplier = supplierDAO.getSupplierById(id);
//	    return supplier.map(ResponseEntity::ok)
//	                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//	}

	
	
	
	
	
	
	
	
	
	
	
	
}
