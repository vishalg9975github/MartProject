package com.asamart.service;

import java.util.List;

import com.asamart.model.SubCategory;

public interface SubCategoryService {
	public SubCategory getSubCategoryById(Integer Id);

	public List<SubCategory> getSubCategory();

	// Add SubCategory
	public SubCategory saveSubCategory(SubCategory subCategory);

	public SubCategory updateSubCategory(Integer id, SubCategory subCategory);

	// soft delete
	public void softDeleteSubCategory(Integer id);

}
