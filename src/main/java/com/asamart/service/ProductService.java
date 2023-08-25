package com.asamart.service;

import java.io.IOException;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.Product;

public interface ProductService {

	public Product saveProduct(Product pd);

	// Update the product by Id
	public List<Product> getProductList();

	public Product updateProductById(int id, Product product);

	// Get Product by Id
	public Product getProductById(Integer Id);

	// soft delete
	public void softDeleteProduct(Integer id);

//to save the multiple product-images
	void saveProductWithImages(String productname, String pdoductdescription, String brand, String tags,
			String productcode, boolean featured, List<MultipartFile> images) throws IOException, Exception;

}
