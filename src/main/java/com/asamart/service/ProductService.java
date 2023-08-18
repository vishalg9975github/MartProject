package com.asamart.service;


import java.util.List;

import com.asamart.model.Product;

public interface ProductService {
	
	// Update the product by Id 
	public List<Product> getProduct();
	public Product updateProductById(int id,Product product);

	
	public void deleteProduct(Integer id);
	

}
