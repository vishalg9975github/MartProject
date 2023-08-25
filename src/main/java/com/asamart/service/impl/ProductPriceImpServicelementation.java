package com.asamart.service.impl;


import java.util.List ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.asamart.controller.ProductController;

import com.asamart.controller.ProductPriceController;
import com.asamart.model.ProductPrice;
import com.asamart.repository.ProductPriceRepository;
import com.asamart.service.ProductPriceService;

@Service
public class ProductPriceImpServicelementation implements ProductPriceService {
	// @Younus K Shaikh
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
		ProductPrice product = productPriceRepository.findById(id).get();
		return product;
	}

	// @Auther - Younus K Shaikh
	// Update ProductPrice by using Price Id

	@Override
	public ProductPrice updateProductPriceById(int id, ProductPrice productPrice) {
		logger.info("Update the productPrice details by Id");
		int ppid = productPrice.getPriceId();
		ppid = id;
		ProductPrice productPrice2 = new ProductPrice();

		productPrice2 = productPriceRepository.findById(id).get();
		productPrice2.setRegularPrice(productPrice.getRegularPrice());
		productPrice2.setSellPrice(productPrice.getSellPrice());
		productPrice2.setUnit(productPrice.getUnit());
		productPrice2.setDeleted(false);
		return productPriceRepository.save(productPrice2);

	}

	@Override
	public List<ProductPrice> getAllDetails() {
		// logger.info("Get all the productPrice details ");
		return productPriceRepository.findAll();

	}

	/* @Author:Nilesh D. L. */
	@Override
	public String softDeleteById(Integer id) {
		Optional<ProductPrice> productPrice = productPriceRepository.findById(id);

		if (productPrice.isPresent()) {
			ProductPrice productPrices = productPrice.get();
			 if (productPrices.isDeleted()) {
	                throw new EmptyResultDataAccessException(1);
	            }
			productPrices.setDeleted(true);
			productPriceRepository.save(productPrices);
			return "Soft Delete Successful ";
		}

		return "Record Not Found";
	}

	


}
