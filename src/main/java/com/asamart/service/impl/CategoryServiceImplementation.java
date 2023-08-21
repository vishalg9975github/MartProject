package com.asamart.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asamart.model.Category;
import com.asamart.repository.CategoryRepository;
import com.asamart.service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImplementation.class);
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		logger.info(" in category service implementation class, add category details ");
		return categoryRepository.save(category);
	}

	@Override
	public Category getCategoryById(Integer id) {
		logger.info("in category service implementation class , get category by Id");
		// Implement the logic to retrieve a category by its ID from the database
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public Category updateCategory(Category category) {
		return categoryRepository.save(category);

	}

//Author: Summit Gaikwad
	@Override
	public List<Category> getAllDetails() {
		return categoryRepository.findAll();

	}

	/* @Author- vishal Waghmare */
	@Transactional
	@Override
	public void softdeleteCategory(Integer id) {
		// Soft delete by updating the isDeleted flag
		Category category = categoryRepository.findById(id).orElseGet(null);
		if (category != null) {
			category.setDeleted(true);
			categoryRepository.save(category);
		}
	}

}
