package com.asamart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.model.SubCategory;
import com.asamart.repository.SubCategoryRepository;
import com.asamart.service.SubCategoryService;
@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	@Autowired
	public SubCategoryRepository subCategoryRepository;

	@Override
	public SubCategory saveSubCategory(SubCategory subCategory) {
		// TODO Auto-generated method stub
		return subCategoryRepository.save(subCategory);
	}

}
