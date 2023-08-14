package com.asamart.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.model.SubCategory;
import com.asamart.service.SubCategoryService;

@RestController
public class SubCategoryController {
	
	
	private static final Logger logger =LoggerFactory.getLogger(SubCategoryController.class);
	@Autowired
	private SubCategoryService subCategoryService;

	@PostMapping("/saveSubCategory")
	public ResponseEntity<SubCategory> saveSubCategory(@RequestBody SubCategory subCategory) {

		SubCategory sc = subCategoryService.saveSubCategory(subCategory);
		logger.info("In subcategory controller save SubCategory method");
		return ResponseEntity.ok().body(sc);
	}
}
