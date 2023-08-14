package com.asamart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.model.Category;
import com.asamart.service.CategoryService;

@RestController

public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//Auther=Nilesh D. Limbalkar
	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){

		Category savedCategory = categoryService.addCategory(category);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);

	}








}
