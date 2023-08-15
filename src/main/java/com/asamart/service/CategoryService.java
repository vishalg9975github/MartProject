package com.asamart.service;

import java.util.List;

import com.asamart.model.Category;

public interface CategoryService {

	Category addCategory(Category category);

	Category getCategoryById(Integer id);

	Category updateCategory(Category category);
	
	List<Category> getAllDetails();

}
