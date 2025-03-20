package com.supplychainmanagement.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.supplychainmanagement.DAO.ProductDAO;
import com.supplychainmanagement.DAO.SupplierDAO;
import com.supplychainmanagement.DTO.ProductDTO;
import com.supplychainmanagement.DTO.ResponseStructure;
import com.supplychainmanagement.Entity.Product;
import com.supplychainmanagement.Entity.Supplier;



@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private SupplierDAO supplierDAO;
	
	public ResponseEntity<ResponseStructure<Product>> addProduct(Product product) {
 		ResponseStructure<Product> structure = new ResponseStructure<Product>();
        Optional<Supplier> optionalSupplier = supplierDAO.getSupplierById(product.getSupplier().getId());

        if (optionalSupplier.isEmpty()) {
            structure.setStatuscode(HttpStatus.NOT_FOUND.value());
            structure.setMessage("Supplier not found with ID: " + product.getSupplier().getId());
            structure.setData(null);
            return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
        }
		else {
              // Set supplier reference in product
				 product.setSupplier(optionalSupplier.get());
    	     Product productInfo = productDAO.saveProductDAO(product); // Save product
             structure.setStatuscode(HttpStatus.CREATED.value());
             structure.setMessage("Product created successfully.");
             structure.setData(productInfo);

        return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
       }

    
  }
	//in this service class i get the product details along with the supplier details so create a helper class known as productDTO to return
	//only product info with supplier_id
	public ResponseEntity<ResponseStructure<ProductDTO>> getProductInfoByIdService(int id) {
		
		Optional<Product> productInfo=productDAO.getProductById(id);
		ResponseStructure<ProductDTO> structure = new ResponseStructure<ProductDTO>();
		
		if(!productInfo.isEmpty()) {
			 ProductDTO productDTO = new ProductDTO(productInfo.get());
			structure.setStatuscode(HttpStatus.FOUND.value());
			structure.setMessage("The product data is found by given id: "+id);
			structure.setData(productDTO);
			return new ResponseEntity<ResponseStructure<ProductDTO>>(structure,HttpStatus.FOUND );
		}
		else {
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("The product data is not found by given id: "+id);
			return new ResponseEntity<ResponseStructure<ProductDTO>>(structure,HttpStatus.FOUND );
		}
	}
	public ResponseEntity<ResponseStructure<List<ProductDTO>>> getAllProductDetailsService(Product product) {
		// TODO Auto-generated method stub
		List<Product> productList=productDAO.getAllProducts();
		ResponseStructure<List<ProductDTO>> structure = new ResponseStructure<List<ProductDTO>>();
		if (!productList.isEmpty()) {
	        // Manually convert Product to ProductDTO
	        List<ProductDTO> dtoList = new ArrayList<>();
	        for (Product products : productList) {
	            ProductDTO dto = new ProductDTO(products);//fetching the id,name,price,stock quantity,supplier_id
	            dtoList.add(dto);
	        }

	        structure.setStatuscode(HttpStatus.FOUND.value());
	        structure.setMessage("Products found successfully.");
	        structure.setData(dtoList);

	        return new ResponseEntity<ResponseStructure<List<ProductDTO>>>(structure, HttpStatus.FOUND);
	    } else {
	        structure.setStatuscode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("No products available.");
	        structure.setData(null); 
	        return new ResponseEntity<ResponseStructure<List<ProductDTO>>>(structure, HttpStatus.NOT_FOUND);
	    }
	}
	public ResponseEntity<ResponseStructure<List<Product>>> getProductInfoBySupplierIdService(int supplier_id) {
		// TODO Auto-generated method stub
		List<Product> productDetails=productDAO.findInfoBySupplierId(supplier_id);
		ResponseStructure<List<Product>> structure= new ResponseStructure<List<Product>>();
		if(!productDetails.isEmpty()) {
			structure.setStatuscode(HttpStatus.FOUND.value());
	        structure.setMessage("Products found successfully.");
	        structure.setData(productDetails);
	        return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.FOUND);
		}
		else {
	        structure.setStatuscode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("No products available.");
	        structure.setData(null); 
	        return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.NOT_FOUND);
	    }
	}
	public ResponseEntity<ResponseStructure<Product>> updateProductInfoService(Product product) {
		// TODO Auto-generated method stub
		Optional<Product> products=productDAO.getProductById(product.getId());
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (products.isPresent()) {
	        Product updatedProducts = productDAO.updateProduct(product); 
	        structure.setStatuscode(HttpStatus.OK.value());
	        structure.setMessage("The record is updated successfully");
	        structure.setData(updatedProducts);

	        return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
	    } 
	    else {
	        structure.setStatuscode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Product not found with ID: " + product.getId());
	        structure.setData(null);
	        return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
	    }

		
	
	}
	public ResponseEntity<ResponseStructure<Product>> deleteProductInfoService(int id) {
		// TODO Auto-generated method stub
		Optional<Product> opt=productDAO.getProductById(id);//it automatically 1 no findById()
		ResponseStructure<Product> structure= new ResponseStructure<Product>();
		if(opt.isPresent()) {
			productDAO.deleteSupplierInfo(opt.get());
			structure.setStatuscode(HttpStatus.OK.value());
			structure.setMessage("the record is deleted");
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
			
			
		}
		else {
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Supplier not found with ID: " + id);
	        structure.setData(null);
	        return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	
	
	
	
}
