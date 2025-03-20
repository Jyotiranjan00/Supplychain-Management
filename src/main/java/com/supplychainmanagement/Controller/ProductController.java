package com.supplychainmanagement.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychainmanagement.DTO.ProductDTO;
import com.supplychainmanagement.DTO.ResponseStructure;
import com.supplychainmanagement.Entity.Product;
import com.supplychainmanagement.Service.ProductService;

@RestController
@RequestMapping("/jsp/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//apis and endpoint
	//addProduct
	@PostMapping
    public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
	
	//getProductById
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<ProductDTO>> getProductById(@PathVariable int id){
		
		return productService.getProductInfoByIdService(id);
	}
	
	//getAllProduct
	@GetMapping
	public ResponseEntity<ResponseStructure<List<ProductDTO>>> getAllProductDetails(Product product){
		return productService.getAllProductDetailsService(product);
	}
	
	//getProductBySupplierId
	@GetMapping("/supid/{supplier_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> getProductBySupplierId(@PathVariable int supplier_id){
		
		return productService.getProductInfoBySupplierIdService(supplier_id);
	}
	
	//updateProduct
	@PutMapping
    public ResponseEntity<ResponseStructure<Product>> updateSupplier(@RequestBody Product product){
		
		return productService.updateProductInfoService(product);
	}
	
	//deleteProduct
	@DeleteMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Product>> deleteProducts(@PathVariable int id){
		
		return productService.deleteProductInfoService(id);
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}
