
package com.asamart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.controller.SubCategoryController;
import com.asamart.model.SubCategory;
import com.asamart.repository.SubCategoryRepository;
import com.asamart.service.SubCategoryService;

@Service
public class SubCategoryServiceImplementation implements SubCategoryService {

	private static final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	/* @Author Ankita Ghayal */
	@Override
	public SubCategory getSubCategoryById(Integer Id) {
		SubCategory subCategory = subCategoryRepository.findById(Id).orElse(null);
		logger.info("In subcategory controller get SubCategory method");

		if (subCategory != null) {
			if (subCategory.isDeleted() == false) {

				return subCategory;
			}
		}
		return null;
	}

	/* @Author Ankita Ghayal */
	@Override
	public List<SubCategory> getSubCategory() {
		List<SubCategory> subCategoryList = new ArrayList<>();
		subCategoryList = subCategoryRepository.findAll();
		logger.info("In subcategory controller get SubCategoryList method");
		return subCategoryList;
	}

	@Override
	public SubCategory saveSubCategory(SubCategory subCategory) {

		logger.info("In subcategory controller save SubCategory method");
		return subCategoryRepository.save(subCategory);
	}

	// @Author Suraj Wankhede
	@Override
	public SubCategory updateSubCategory(Integer id, SubCategory subCategory) {
		logger.info("In subcategory controller update SubCategory method");

		int pid = subCategory.getSubcategoryid();
		pid = id;

		SubCategory sub = subCategoryRepository.findById(pid).get();
		sub.setSubcategoryname(subCategory.getSubcategoryname());
		sub.setCreatedBy(subCategory.getCreatedBy());
		sub.setCategoryid(subCategory.getCategoryid());
		sub.setDescription(subCategory.getDescription());
		sub.setDeleted(subCategory.isDeleted());

		return subCategoryRepository.save(sub);
	}

	// @Author Swapnil Gawai
	@Transactional
	public void softDeleteSubCategory(Integer subCategoryId) {
		SubCategory subCategory = subCategoryRepository.findById(subCategoryId).orElse(null);
		if (subCategory != null) {
			subCategory.setDeleted(false);
			subCategoryRepository.save(subCategory);
		}
	}
}
