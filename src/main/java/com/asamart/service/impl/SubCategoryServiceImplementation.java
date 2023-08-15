package com.asamart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.controller.SubCategoryController;
import com.asamart.controller.TestController;
import com.asamart.exceptions.SubCategoryNotFoundException;
import com.asamart.model.SubCategory;
import com.asamart.repository.SubCategoryRepository;
import com.asamart.service.SubCategoryService;

@Service
public class SubCategoryServiceImplementation implements SubCategoryService{
	
	private static final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	
	@Override
	public SubCategory getSubCategoryById(Integer Id) {
		
		if(subCategoryRepository.findById(Id).isEmpty())
			throw new SubCategoryNotFoundException("Requested SubCategoryId does not exist");
		SubCategory subCategory = subCategoryRepository.findById(Id).get();
		return subCategory;
	}
}