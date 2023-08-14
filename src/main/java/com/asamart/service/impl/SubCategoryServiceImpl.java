package com.asamart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.controller.SubCategoryController;
import com.asamart.model.SubCategory;
import com.asamart.repository.SubCategoryRepository;
import com.asamart.service.SubCategoryService;
@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	private static final Logger logger =LoggerFactory.getLogger(SubCategoryController.class);
	@Autowired
	public SubCategoryRepository subCategoryRepository;

	@Override
	public SubCategory saveSubCategory(SubCategory subCategory) {

		logger.info("In subcategory controller save SubCategory method");
		return subCategoryRepository.save(subCategory);
	}

}
