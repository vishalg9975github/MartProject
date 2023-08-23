package com.asamart.service;

import java.util.List;

import com.asamart.model.Product;

public interface ProductService {

	public Product saveProduct(Product pd);

	// Update the product by Id
	public List<Product> getProduct();

	public Product updateProductById(int id, Product product);

	// Get Product by Id
	public Product getProductById(Integer Id);

	// soft delete
	public void softDeleteProduct(Integer id);

}
