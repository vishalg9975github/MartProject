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

}
