package com.asamart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.controller.SubCategoryController;
import com.asamart.model.SubCategory;
import com.asamart.repository.SubCategoryRepository;
import com.asamart.service.SubCategoryService;
@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	private static final Logger logger =LoggerFactory.getLogger(SubCategoryController.class);
	@Autowired
	public SubCategoryRepository subCategoryRepository;

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

}
