package com.asamart.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.asamart.model.Category;

public interface CategoryService {

	Category addCategory(Category category);

	Category getCategoryById(Integer id);

	Category updateCategory(Category category);

	List<Category> getAllDetails();

	public void softdeleteCategory(Integer id);
}
