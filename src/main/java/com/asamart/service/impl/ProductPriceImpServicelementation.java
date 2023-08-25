package com.asamart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.controller.ProductController;
import com.asamart.controller.ProductPriceController;
import com.asamart.exceptions.SubCategoryNotFoundException;
import com.asamart.model.ProductPrice;
import com.asamart.model.SubCategory;
import com.asamart.repository.ProductPriceRepository;
import com.asamart.repository.ProductRepository;
import com.asamart.service.ProductPriceService;
@Service
public class ProductPriceImpServicelementation implements ProductPriceService {

	private static final Logger logger = LoggerFactory.getLogger(ProductPriceController.class);
	
	/* @Author: Sumit Gaikwad */
	@Autowired
	private ProductPriceRepository productPriceRepository;

	@Override
	public ProductPrice savePriceDetails(ProductPrice priceDetails) {
		return productPriceRepository.save(priceDetails);
	}
	
	
	/* @Author: Suraj Wankhede */
	@Override
	public ProductPrice getProductPrice(Integer id) {
		logger.info("in ProductPrice, getProduct Data");
		ProductPrice product=productPriceRepository.findById(id).get();
		return product;
	}



}
