
package com.asamart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.controller.SubCategoryController;
import com.asamart.exceptions.SubCategoryNotFoundException;
import com.asamart.model.SubCategory;
import com.asamart.repository.SubCategoryRepository;
import com.asamart.service.SubCategoryService;

@Service
public class SubCategoryServiceImplementation implements SubCategoryService{

	private static final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	/* @Author Ankita Ghayal */
	@Override
	public SubCategory getSubCategoryById(Integer Id) {
		if(subCategoryRepository.findById(Id).isEmpty())
			throw new SubCategoryNotFoundException("Requested SubCategoryId does not exist");
		SubCategory subCategory = subCategoryRepository.findById(Id).get();
		logger.info("In subcategory controller get SubCategory method");
		return subCategory;
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

	@Override
	public SubCategory updateSubCategory(Integer subcategoryid, SubCategory updatesubCategory) {
		logger.info("In subcategory controller update SubCategory method");

		SubCategory sub1= subCategoryRepository.findById(subcategoryid).orElse(null);
		if(sub1!=null)
		{

			sub1.setSubcategoryname(updatesubCategory.getSubcategoryname());
			sub1.setCreatedBy(updatesubCategory.getCreatedBy());
			sub1.setDescription(updatesubCategory.getDescription());
			sub1.setCategoryid(updatesubCategory.getCategoryid());

			return subCategoryRepository.save(sub1);
		}
		else
		{
			throw new RuntimeException("SubCategeory not found");
		}

	}
	
    //@Author Swapnil Gawai
	@Transactional
	public void softDeleteSubCategory(Integer subCategoryId) {
		SubCategory subCategory=subCategoryRepository.findById(subCategoryId).orElse(null);
		if (subCategory != null) {
			subCategory.setDeleted(false);
			subCategoryRepository.save(subCategory);
		}
	}
}