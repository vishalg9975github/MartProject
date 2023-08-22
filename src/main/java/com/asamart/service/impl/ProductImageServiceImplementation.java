package com.asamart.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import java.nio.file.StandardCopyOption;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

//import com.asamart.exceptions.ProductImageNotFoundException;
//import com.asamart.exceptions.ProductImageUpdateException;
import com.asamart.model.Product;
import com.asamart.model.ProductImage;
import com.asamart.repository.ProductImageRepository;
import com.asamart.service.ProductImageService;

@Service
public class ProductImageServiceImplementation implements ProductImageService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplementation.class);
	@Autowired
	private ProductImageRepository productImageRepository;
	@Value("${UPLOAD_DIR}")
	private String uploadDirectory;

	/* @Auther - shiwani dewang */
	@Override
	public ProductImage addProductImage(ProductImage productImage, MultipartFile image) throws IOException {
		String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
		Path imagePath = Paths.get(uploadDirectory, filename);
		Files.write(imagePath, image.getBytes());
		logger.debug("In ProductImageService implementation class,addProductImage method");
		productImage.setImagePath(imagePath.toString());
		return productImageRepository.save(productImage);

	}

	/* @ Auther shiwani dewang */
	@Override
	public ProductImage updateProductImage(int imageId, ProductImage updatedImage, MultipartFile image)
			throws IOException {
		ProductImage existingImage = productImageRepository.findById(imageId)
				.orElseThrow(() -> new EntityNotFoundException("Product image not found"));
		logger.debug("In ProductImageservice implementation class,updateProductImage method");
		// existingImage.setProductid(updatedImage.getProductid());
		existingImage.setDefaultImage(updatedImage.isDefaultImage());
		logger.info("In ProductImage service implementation class, updateproductImage method");
		// Save new image file if provided
		if (image != null && !image.isEmpty()) {
			// Delete old image
			System.out.println("Deleting previous image: " + existingImage.getImagePath());
			Files.deleteIfExists(Paths.get(existingImage.getImagePath()));

			// Save new image file
			String filename = image.getOriginalFilename();
			Path imagePath = Paths.get(uploadDirectory, filename);
			Files.write(imagePath, image.getBytes());
			existingImage.setImagePath(imagePath.toString());
		}

		return productImageRepository.save(existingImage);

	}

	// Author sachin more
	@Override
	public void deleteProductImage(Integer imageId) {
		productImageRepository.deleteById(imageId);

	}

	// Author sachin more
	// soft delete in db
	@Transactional
	public void softDeleteProduct(Integer productImageId) {
		ProductImage productImage = productImageRepository.findById(productImageId).orElse(null);
		if (productImage != null) {
			productImage.setDeleted(true);
			productImageRepository.save(productImage);
		}
	}

	// Author sachin more
	// recover deleted productimage
	@Transactional
	public void recoverDeletedProduct(Integer productImageId) {
		ProductImage productImage = productImageRepository.findById(productImageId).orElse(null);
		if (productImage != null) {
			productImage.setDeleted(false);
			productImageRepository.save(productImage);
		}
	}

	// @Author Swapnil Gawai
	@Override
	public List<ProductImage> getAllImages() {

		return productImageRepository.findAll();
	}

	/* @Auther shiwani dewang */
	@Override
	public boolean imageExistsInDatabase(String imageHash) {
		return productImageRepository.existsByImageHash(imageHash);
	}

	@Override
	public void saveProductImage(ProductImage productImage) {
		productImageRepository.save(productImage);

	}

}
