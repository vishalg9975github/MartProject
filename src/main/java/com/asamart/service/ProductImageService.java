package com.asamart.service;


import java.io.IOException;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.ProductImage;

public interface ProductImageService {

	
	// Add ProductImage  
	ProductImage addProductImage(ProductImage productImage);

	// Update ProductImage
	ProductImage updateProductImage(int imageId, int productid, boolean defaultImage, MultipartFile image);

	//Get ProductImages by id
	
	public ProductImage getProductImageById(int imageId);
	


	List<ProductImage> getAllImages();

	ProductImage addProductImage(ProductImage productImage, MultipartFile imageFile) throws IOException;

	ProductImage updateProductImage(int imageId, ProductImage updatedImage, MultipartFile newImageFile)
			throws IOException;

	public void deleteProductImage(Integer imageId);
	
	
	
}
