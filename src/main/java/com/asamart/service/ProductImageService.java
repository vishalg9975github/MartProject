package com.asamart.service;

import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.ProductImage;

public interface ProductImageService {
	ProductImage addProductImage(ProductImage productImage);

	ProductImage updateProductImage(int imageId, int productid, boolean defaultImage, MultipartFile image);

	public void deleteProductImage(Integer imageId);

	public void softDeleteProduct(Integer id);

	public void recoverDeletedProduct(Integer id);
}
