package com.asamart.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.ProductImage;

public interface ProductImageService {
	
	// Add ProductImage  
	ProductImage addProductImage(ProductImage productImage);

	// Update ProductImage
	ProductImage updateProductImage(int imageId, int productid, boolean defaultImage, MultipartFile image);

	//Get All ProductImages
	
	List<ProductImage> getAllProductImage();
	
}
