package com.asamart.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.Category;
import com.asamart.service.CategoryService;

@RestController
@RequestMapping("/Category")
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	private CategoryService categoryService;

	/* @Author Nilesh */ // modified By shiwani
	@PostMapping("/addCategory")
	public Category createCategory(@RequestParam("file") MultipartFile file,
			@RequestParam("categoryname") String categoryname, @RequestParam("description") String description,
			@RequestParam("createddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createddate,
			@RequestParam("createdBy") String createdBy) {
		logger.info("In rest controller class, add category details");
		try {
			byte[] imageBytes = file.getBytes();

			Category category = new Category();
			category.setCategoryname(categoryname);
			category.setDescription(description);
			category.setCreateddate(new Date());
			category.setCreatedBy(createdBy);
			category.setImage(imageBytes);

			return categoryService.addCategory(category);
		} catch (IOException e) {
			e.printStackTrace();
			// Handle the exception as needed
			return null;
		}
	}

	/* @Author Shiwani Dewang */
	@GetMapping("/getCategory/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
		Category category = categoryService.getCategoryById(id);
		logger.info("In rest controller class , get category details by Id");

		if (category == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(category);
	}

	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<Map<String, Object>> updateCategory(@PathVariable Integer id,
			@RequestParam("file") MultipartFile file, @RequestParam("categoryname") String categoryname,
			@RequestParam("description") String description, @RequestParam("createdBy") String createdBy) {
		Category existingCategory = categoryService.getCategoryById(id);

		if (existingCategory == null) {
			return ResponseEntity.notFound().build();
		}

		try {
			byte[] imageBytes = file.getBytes();

			existingCategory.setCategoryname(categoryname);
			existingCategory.setDescription(description);

			existingCategory.setCreatedBy(createdBy);
			existingCategory.setImage(imageBytes);

			Category savedCategory = categoryService.updateCategory(existingCategory);
			// Create a custom response with updated category details and a message
			Map<String, Object> response = new HashMap<>();
			response.put("category", savedCategory);
			response.put("message", "Category updated successfully.");

			return ResponseEntity.ok(response);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	/* Author:Sumit Gaikwad */
	@GetMapping("/allCategory")
	public List<Category> getAllDetails() {
		List<Category> allCategoryDetails = categoryService.getAllDetails();
		// Return all category Details
		return allCategoryDetails;

	}

	/* @Author- vishal waghmare */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> softdeleteCategoryById(@PathVariable("id") Integer id) {
		logger.info("In rest controller class , delete category details by Id");
		categoryService.softdeleteCategory(id);
		return ResponseEntity.ok("category soft deleted successfully");
	}

}
