package com.asamart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.asamart.model.SubCategory;
import com.asamart.service.SubCategoryService;

@RestController
public class SubCategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);
	@Autowired
	private SubCategoryService subCategoryService;
	
	/* @Author Ankita Ghayal */
	@GetMapping("/getSubcategory/{id}")
 	public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable("id") Integer id){
		SubCategory subCategory = subCategoryService.getSubCategoryById(id);
		logger.info("In the Controller class,getSubCategoryById method");
		return ResponseEntity.ok().body(subCategory);
	}
	
	@PostMapping("/saveSubCategory")
	public ResponseEntity<SubCategory> saveSubCategory(@RequestBody SubCategory subCategory) {

		SubCategory sc = subCategoryService.saveSubCategory(subCategory);
		logger.info("In subcategory controller save SubCategory method");
		return ResponseEntity.ok().body(sc);
	}
	
	/* @Author Suraj Wankhede */
	@PutMapping("/updateSubcategeory/{id}")
	public ResponseEntity<SubCategory> updateSubCategeory(@PathVariable Integer id, @RequestBody SubCategory updatesubCategory)
	{
		SubCategory sub1=subCategoryService.updateSubCategory(id, updatesubCategory);
		logger.info("In subcategory controller update SubCategory method ");
		return ResponseEntity.ok().body(sub1);
	}
}

