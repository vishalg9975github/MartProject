package com.asamart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.model.SubCategory;
import com.asamart.service.SubCategoryService;

@RestController
public class SubCategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);
	@Autowired
	private SubCategoryService subCategoryService;

	@GetMapping("/getSubcategory/{id}")
 	public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable("id") Integer id){
		SubCategory subCategory = subCategoryService.getSubCategoryById(id);
		logger.info("In the Controller class,getSubCategoryById method");
		return ResponseEntity.ok().body(subCategory);
	}
}
 