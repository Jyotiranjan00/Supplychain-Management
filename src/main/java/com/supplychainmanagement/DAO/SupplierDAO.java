package com.supplychainmanagement.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.supplychainmanagement.Entity.Product;
import com.supplychainmanagement.Entity.Supplier;
import com.supplychainmanagement.Repository.SupplierRepository;
@Repository
public class SupplierDAO {
	
	@Autowired
	private SupplierRepository supplierRepository;

	public Supplier saveSupplierDAO(Supplier supplier) {
		
		return supplierRepository.save(supplier);
	}

	public Optional<Supplier> getSupplierInfoByIdDAO(int id) {
		
		return supplierRepository.findById(id);
	}

	public List<Supplier> getAllSupplierInfoDAO() {
		
		return supplierRepository.findAll();
	}
	
	//update for 2 methods
	public Optional<Supplier> getSupplierById(int id) {//return findbyid()
		
		return supplierRepository.findById(id);
	}

	public Supplier updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierRepository.save(supplier);
	}

	public void deleteSupplierInfo(Supplier supplier) {
		// TODO Auto-generated method stub
		 supplierRepository.delete(supplier);
		
	}

	
	

	

	
	
	
	

}
