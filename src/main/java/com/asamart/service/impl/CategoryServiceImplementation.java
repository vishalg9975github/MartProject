package com.asamart.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.Category;
import com.asamart.model.ProductImage;
import com.asamart.repository.CategoryRepository;
import com.asamart.service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImplementation.class);
	@Autowired
	private CategoryRepository categoryRepository;
	@Value("${upload.dir}")
	private String uploadDirectory;

	@Override
	public Category addCategory(Category category, MultipartFile image) throws IOException {
		String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
		Path imagePath = Paths.get(uploadDirectory, filename);
		Files.write(imagePath, image.getBytes());
		logger.debug("In categoryService implementation class,addCategory method");
		category.setImagePath(imagePath.toString());
		return categoryRepository.save(category);
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
		existingCategory.setCreateddate(new Date ());
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

	

}
