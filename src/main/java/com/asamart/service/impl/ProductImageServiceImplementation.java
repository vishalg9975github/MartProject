package com.asamart.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;

import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import com.asamart.repository.ProductRepository;
import com.asamart.service.ProductImageService;

@Service
public class ProductImageServiceImplementation implements ProductImageService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplementation.class);
	@Autowired
	private ProductImageRepository productImageRepository;
	@Autowired
	private ProductRepository productRepository;
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
		// existingImage.setImageName(updatedImage.getImageName());
		logger.info("In ProductImage service implementation class, updateproductImage method");
		// Save new image file if provided
		if (image != null && !image.isEmpty()) {
			String newImageName = image.getOriginalFilename();
			existingImage.setImageName(newImageName);
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

	/* @ Auther Anushka */

	@Override
	public ProductImage getProductImageById(int imageId) {
		logger.info("In ProductImage Service Implementation Class, getProductImageById method . ");

		ProductImage productImage = productImageRepository.getProductImageAndNotDeleted(imageId);
		return productImage;

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

//@auther shiwani dewang
	@Override
	public void saveProductImage(ProductImage productImage) {
		productImageRepository.save(productImage);

	}

	@Override
	public void addImagesToProduct(Integer productid, List<MultipartFile> images) throws Exception {
		Product product = productRepository.findByproductid(productid);

		if (product == null) {
			throw new Exception("Product with the code " + productid + " not found.");
		}

		for (MultipartFile image : images) {
			byte[] imageBytes = image.getBytes();
			String imageHash = calculateImageHash(imageBytes);
			String imageName = image.getOriginalFilename();
			if (imageExistsInDatabase(imageHash)) {
				throw new Exception("Duplicate image detected, please rename the image: " + imageName);
			}

			String imagePath = saveImageToFolder(imageBytes, imageHash, imageName);

			ProductImage productImage = new ProductImage();
			productImage.setImageHash(imageHash);
			productImage.setImagePath(imagePath);
			productImage.setDefaultImage(false);
			productImage.setImageName(imageName);
			productImage.setProduct(product);

			productImageRepository.save(productImage);
		}

	}

	private String calculateImageHash(byte[] imageBytes) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = md.digest(imageBytes);

			StringBuilder hexString = new StringBuilder();
			for (byte hashByte : hashBytes) {
				String hex = Integer.toHexString(0xff & hashByte);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error calculating image hash.", e);
		}
	}

	private String saveImageToFolder(byte[] imageBytes, String imageHash, String imageName) {
		// Logic to save the image to the images folder
		String imagePath = "src/main/resources/images/" + imageHash + ".jpg"; // Change to your actual path

		try {
			Files.write(Paths.get(imagePath), imageBytes);
			return imagePath;
		} catch (IOException e) {
			throw new RuntimeException("Error saving image.", e);
		}
	}

}
