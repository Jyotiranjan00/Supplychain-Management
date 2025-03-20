package com.supplychainmanagement.DAO;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.supplychainmanagement.Entity.Product;
import com.supplychainmanagement.Repository.ProductRepository;
@Repository
public class ProductDAO {
	
	@Autowired
	private ProductRepository productRepository;
	 // Save Product
    public Product saveProductDAO(Product product) {
        return productRepository.save(product);
    }

    // Get Product by ID
    public Optional<Product> getProductById(int id) {//2
        return productRepository.findById(id);
    }

    // Get all Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

	public List<Product> findInfoBySupplierId(int supplier_id) {
		// custom methods
		return productRepository.findBySupplierId(supplier_id);
	}

	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	

	public void deleteSupplierInfo(Product product) {
		// TODO Auto-generated method stub
		productRepository.delete(product);
		
	}
	public List<Product> getProductsByIds(List<Integer> productIds) {
        return productRepository.findAllById(productIds);
    }

    public void saveAllProducts(List<Product> products) { // ✅ Save products after setting order reference
        productRepository.saveAll(products);
    }

	

	
}
