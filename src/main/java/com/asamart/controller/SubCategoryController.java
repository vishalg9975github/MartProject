package com.asamart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.model.SubCategory;
import com.asamart.service.SubCategoryService;

@RestController
public class SubCategoryController {
	@Autowired
	private SubCategoryService subCategoryService;

	@PostMapping("/saveSubCategory")
	public ResponseEntity<SubCategory> saveSubCategory(@RequestBody SubCategory subCategory) {

		SubCategory sc = subCategoryService.saveSubCategory(subCategory);
		return ResponseEntity.ok().body(sc);
	}
}
