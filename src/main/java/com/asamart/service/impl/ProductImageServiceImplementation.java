package com.asamart.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.asamart.exceptions.ProductImageNotFoundException;
import com.asamart.exceptions.ProductImageUpdateException;
import com.asamart.model.ProductImage;
import com.asamart.repository.ProductImageRepository;
import com.asamart.service.ProductImageService;

@Service
public class ProductImageServiceImplementation implements ProductImageService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplementation.class);
	@Autowired
	private ProductImageRepository productImageRepository;

	/* @Auther - shiwani dewang */
	@Override
	public ProductImage addProductImage(ProductImage productImage) {
		ProductImage productImage1 = productImageRepository.save(productImage);
		logger.info("In product service implementation class,addproduct");
		return productImage1;
	}

	/* @ Auther shiwani dewang */
	@Override
	public ProductImage updateProductImage(int imageId, int productid, boolean defaultImage, MultipartFile image) {
		final String UPLOAD_DIR = "E:/ProjectVelocity/image";
		Optional<ProductImage> existingImageOptional = productImageRepository.findById(imageId);
		if (existingImageOptional.isPresent()) {
			logger.info("In productImageService implemnation class, update productImage method");
			ProductImage existingImage = existingImageOptional.get();
			existingImage.setProductid(productid);
			existingImage.setDefaultImage(defaultImage);
			// Update image file if provided
			if (image != null) {
				String fileName = image.getOriginalFilename();
				String filePath = UPLOAD_DIR + File.separator + fileName;

				try {
					Path targetPath = Path.of(filePath);
					Files.copy(image.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
					existingImage.setImagePath(filePath);
				} catch (Exception e) {
					throw new ProductImageUpdateException("Error updating image: " + e.getMessage());
				}

				return productImageRepository.save(existingImage);

			} else {
				throw new ProductImageNotFoundException("Image with ID " + imageId + " not found");
			}
		}
		if (!existingImageOptional.isPresent()) {
			throw new ProductImageNotFoundException("Image with ID " + imageId + " is not exist");
		}
		return null;

	}
	//Author sachin more
	@Override
	public void deleteProductImage(Integer imageId) {
		productImageRepository.deleteById(imageId);
		
		
	}

}
