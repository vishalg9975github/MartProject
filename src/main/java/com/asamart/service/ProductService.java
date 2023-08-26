package com.asamart.service;

import java.io.IOException;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.Product;

public interface ProductService {
	// to save the multiple product-images
	void saveProductWithImages(String productname, String pdoductdescription, String brand, String tags,
			String productcode, boolean featured, List<MultipartFile> images) throws IOException, Exception;

	public Product saveProduct(Product pd);

	public List<Product> getProduct();

	// Update the product by Id
	public Product updateProductById(int id, Product product);

	public void deleteProduct(Integer id);

	// Get Product by Id
	public Product getProductById(Integer Id);

	// soft delete
	public void softDeleteProduct(Integer id);

	// recover deleted
	public void recoverDeletedProduct(Integer id);

///for images
	Product findByproductid(Integer productid);

}
