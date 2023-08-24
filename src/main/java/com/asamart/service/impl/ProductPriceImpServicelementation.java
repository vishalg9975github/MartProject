package com.asamart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
