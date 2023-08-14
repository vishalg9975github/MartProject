package com.asamart.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.Category;
import com.asamart.service.CategoryService;

@RestController
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	private CategoryService categoryService;

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
}
