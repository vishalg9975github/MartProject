package com.asamart.service;

import java.util.List;

import com.asamart.model.SubCategory;

public interface SubCategoryService {
	public SubCategory getSubCategoryById(Integer Id);
	public List<SubCategory> getSubCategory();
	public SubCategory saveSubCategory(SubCategory subCategory);
	public SubCategory updateSubCategory(Integer id,SubCategory subCategory);
}
