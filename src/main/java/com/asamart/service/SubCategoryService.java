package com.asamart.service;

import com.asamart.model.SubCategory;

public interface SubCategoryService {
	public SubCategory getSubCategoryById(Integer Id);
	public SubCategory saveSubCategory(SubCategory subCategory);
	public SubCategory updateSubCategory(Integer id,SubCategory subCategory);
}
