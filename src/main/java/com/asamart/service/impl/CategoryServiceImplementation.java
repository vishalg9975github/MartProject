package com.asamart.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.Category;
import com.asamart.repository.CategoryRepository;
import com.asamart.service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImplementation.class);
	@Autowired
	private CategoryRepository categoryRepository;
	@Value("${upload.dir}")
	private String uploadDirectory;

	/* @Author shiwani-dewang */
	@Override
	@Transactional
	public Category addCategory(Category category, MultipartFile image) throws IOException {
		String categoryname = category.getCategoryname();
		if (categoryRepository.findBycategoryname(categoryname) != null) {
			throw new RuntimeException("Category with the same name already exists: " + categoryname);
		}
		logger.info(" In category service implementation class ,addcategory method");
		byte[] imageBytes = image.getBytes();
		String imageHash = calculateImageHash(imageBytes);
		String imageName = image.getOriginalFilename();

		if (categoryRepository.findByImageHash(imageHash) != null) {
			throw new RuntimeException(
					"Duplicate image detected  Please rename the image." + image.getOriginalFilename());
		}
		String imagePath = saveImageToFolder(imageBytes, imageHash, imageName);
		category.setImagePath(imagePath);
		category.setImageHash(imageHash);
		return categoryRepository.save(category);
	}

	private String calculateImageHash(byte[] imageBytes) {
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = messagedigest.digest(imageBytes);

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
		// Logic to save the image to the categoryImage folder
		String imagePath = "src/main/resources/categoryImages/" + imageHash + ".jpg"; // Change to your actual path

		try {
			Files.write(Paths.get(imagePath), imageBytes);
			return imagePath;
		} catch (IOException e) {
			throw new RuntimeException("Error saving image.", e);
		}
	}

	@Override
	public Category getCategoryById(Integer id) {
		logger.info("in category service implementation class , get category by Id");
		// Implement the logic to retrieve a category by its ID from the database
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public Category updateCategory(int id, Category category, MultipartFile image) throws IOException {
		Category existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("category  not found"));
		logger.debug("In Categoryservice implementation class,updateCategory method");
		existingCategory.setCategoryname(category.getCategoryname());
		existingCategory.setDescription(category.getDescription());
		existingCategory.setCreatedBy(category.getCreatedBy());
		existingCategory.setCreateddate(new Date());
		if (image != null && !image.isEmpty()) {
			// Delete old image
			System.out.println("Deleting previous image: " + existingCategory.getImagePath());
			Files.deleteIfExists(Paths.get(existingCategory.getImagePath()));
			// Save new image file
			String filename = image.getOriginalFilename();
			Path imagePath = Paths.get(uploadDirectory, filename);
			Files.write(imagePath, image.getBytes());
			existingCategory.setImagePath(imagePath.toString());
		}
		return categoryRepository.save(existingCategory);

	}

//Author: Summit Gaikwad
	@Override
	public List<Category> getAllDetails() {
		return categoryRepository.findAll();

	}

	/* @Author- vishal Waghmare */
	@Transactional
	@Override
	public void softdeleteCategory(Integer id) {
		// Soft delete by updating the isDeleted flag
		Category category = categoryRepository.findById(id).orElseGet(null);
		if (category != null) {
			category.setDeleted(true);
			categoryRepository.save(category);
		}
	}

	/* Soft-Delete */
	@Override
	public List<Category> getActiveCategories() {
		return categoryRepository.findByIsDeletedFalse();

	}

	@Override
	public void softDeleteCategory(Integer id) {
		Category category = categoryRepository.findById(id).orElse(null);
		if (category != null) {
			category.setDeleted(true);
			categoryRepository.save(category);
		}
		if (category == null) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public Category getActiveCategoryById(Integer id) throws Exception {
		Category category = categoryRepository.findByIdAndIsDeleted(id, false);
		return category;

	}

	// this once is for images
	@Override
	public void deleteCategoryById(Integer id) {
		Category category = categoryRepository.findById(id).orElse(null);

		if (category != null) {
			deleteImage(category.getImagePath());
			categoryRepository.delete(category);
		}
	}

	private void deleteImage(String imagePath) {
		if (imagePath != null) {
			try {
				Path imageFilePath = Paths.get(uploadDirectory + imagePath);
				Files.deleteIfExists(imageFilePath);
			} catch (IOException e) {
				e.printStackTrace();
				// Handle the exception
			}
		}
	}
}
