package com.asamart.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.ProductImage;

public interface ProductImageService {

	List<ProductImage> getAllImages();

	ProductImage addProductImage(ProductImage productImage, MultipartFile imageFile) throws IOException;

	ProductImage updateProductImage(int imageId, ProductImage updatedImage, MultipartFile newImageFile)
			throws IOException;

	public void deleteProductImage(Integer imageId);
}
