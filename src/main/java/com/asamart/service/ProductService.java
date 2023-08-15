package com.asamart.service;

import com.asamart.model.Product;

public interface ProductService {
	
	// Update the product by Id 
	public Product updateProductById(int id,Product product);

}
