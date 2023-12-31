package com.asamart.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.Category;

public interface CategoryService {

	Category addCategory(Category category, MultipartFile imageFile) throws IOException;

	Category getCategoryById(Integer id);

	Category updateCategory(int id, Category category, MultipartFile imageFile) throws IOException;

	List<Category> getAllDetails();

	public void softdeleteCategory(Integer id);

	List<Category> getActiveCategories();

	void softDeleteCategory(Integer id);

	void deleteCategoryById(Integer id);

	Category getActiveCategoryById(Integer id) throws Exception;

}
