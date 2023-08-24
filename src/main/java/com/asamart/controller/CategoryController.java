package com.asamart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<String> addCategory(@RequestParam("image") MultipartFile image,
			@ModelAttribute Category category) throws IOException {
		try {
			logger.info("Category controller class , add category method");
			categoryService.addCategory(category, image);
			return ResponseEntity.status(HttpStatus.CREATED).body("Category added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	/* @Author Shiwani Dewang */
	@PutMapping("/update/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Integer id,
			@RequestParam(value = "image", required = false) MultipartFile image, @ModelAttribute Category category)
			throws IOException {
		logger.info("In rest controller class , update category details by Id");

		Category category1 = categoryService.updateCategory(id, category, image);
		return ResponseEntity.ok(category1);

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

	/*
	 * for soft delete and to get the active categories which are not soft-deleted
	 * 
	 * @Auther Shiwani dewang
	 */
	@GetMapping("/allActiveCategoryList")
	public List<Category> getActiveCategories() {
		return categoryService.getActiveCategories();
	}

	/* @ Author - shiwani dewang */
	@GetMapping("activeCategory/{id}")
	public Category getActiveCategoryById(@PathVariable Integer id) throws Exception {
		Category category = categoryService.getActiveCategoryById(id);
		if (category != null) {
			return category;
		} else {
			throw new Exception(" Category with Id " + id + " not found !! ");
		}

	}

}
