package com.supplychainmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.supplychainmanagement.DAO.SupplierDAO;
import com.supplychainmanagement.DTO.ResponseStructure;
import com.supplychainmanagement.Entity.Supplier;
@Service
public class SupplierService {
	
	@Autowired
	private SupplierDAO supplierDAO;

	public ResponseEntity<ResponseStructure<Supplier>> saveSupplierInfoService(Supplier supplier) {
		
		Supplier sup=supplierDAO.saveSupplierDAO(supplier);
		ResponseStructure<Supplier> structure= new ResponseStructure<Supplier>();
		structure.setStatuscode(HttpStatus.CREATED.value());
		structure.setMessage("created");
		structure.setData(sup);
		return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Supplier>> getSupplierInfoByIdService(int id) {
		Optional<Supplier> opt=supplierDAO.getSupplierInfoByIdDAO(id);//1
		ResponseStructure<Supplier> structure= new ResponseStructure<Supplier>();
		if(opt.isPresent()) {
			structure.setStatuscode(HttpStatus.FOUND.value());
			structure.setMessage("found");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Supplier>>(structure,HttpStatus.FOUND);
		}
		else {
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("not found");
			return new ResponseEntity<ResponseStructure<Supplier>>(structure,HttpStatus.NOT_FOUND);
		}
		
	}

	public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplierInfoService() {
		List<Supplier> suppliers=supplierDAO.getAllSupplierInfoDAO();
		ResponseStructure<List<Supplier>> structure=new ResponseStructure<List<Supplier>>();
		if(!suppliers.isEmpty()) {
			structure.setStatuscode(HttpStatus.FOUND.value());
			structure.setMessage("found");
			structure.setData(suppliers);
			return new ResponseEntity<ResponseStructure<List<Supplier>>>(structure,HttpStatus.FOUND);
		}
		else {
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("not found");
			return new ResponseEntity<ResponseStructure<List<Supplier>>>(structure,HttpStatus.NOT_FOUND);
		}
		
	}
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplierInfoService(Supplier supplier) {
	    Optional<Supplier> suppliers = supplierDAO.getSupplierById(supplier.getId());
	    ResponseStructure<Supplier> structure = new ResponseStructure<>();

	    if (suppliers.isPresent()) {
	        Supplier updatedSupplier = supplierDAO.updateSupplier(supplier); 
	        structure.setStatuscode(HttpStatus.OK.value());
	        structure.setMessage("The record is updated successfully");
	        structure.setData(updatedSupplier);

	        return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.OK);
	    } 
	    else {
	        structure.setStatuscode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Supplier not found with ID: " + supplier.getId());
	        structure.setData(null);
	        return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<Supplier>> deleteSupplierInfoService(int id) {
		Optional<Supplier> opt=supplierDAO.getSupplierInfoByIdDAO(id);//it automatically 1 no findById()
		ResponseStructure<Supplier> structure= new ResponseStructure<Supplier>();
		if(opt.isPresent()) {
			supplierDAO.deleteSupplierInfo(opt.get());
			structure.setStatuscode(HttpStatus.OK.value());
			structure.setMessage("the record is deleted");
			return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.OK);
		}
		else {
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Supplier not found with ID: " + id);
	        structure.setData(null);
	        return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.NOT_FOUND);
			
		}
	}

}
	

 

