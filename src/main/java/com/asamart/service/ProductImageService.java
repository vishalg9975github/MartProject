package com.asamart.service;

import java.io.IOException;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.Category;
import com.asamart.model.ProductImage;

public interface ProductImageService {

	ProductImage addProductImage(ProductImage productImage, MultipartFile imageFile) throws IOException;

	ProductImage updateProductImage(int imageId, ProductImage updatedImage, MultipartFile newImageFile)
			throws IOException;

// Get ProductImages by id
	public ProductImage getProductImageById(int imageId);

	List<ProductImage> getAllImages();

	public void softDeleteProduct(Integer id);

	// these are for product with to add multiple images//

	boolean imageExistsInDatabase(String imageHash);

	void saveProductImage(ProductImage productImage);

	void addImagesToProduct(Integer productCode, List<MultipartFile> images) throws Exception;

}
