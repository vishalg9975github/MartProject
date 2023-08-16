package com.asamart.service;


import com.asamart.model.Product;

public interface ProductService {
	
	// Update the product by Id 
	public Product updateProductById(int id,Product product);

	
	public void deleteProduct(Integer id);
	
	//Get Product by Id
	public Product getProductById(Integer Id);
	

}
