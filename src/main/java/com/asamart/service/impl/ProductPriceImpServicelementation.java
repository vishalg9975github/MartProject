package com.asamart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.model.ProductPrice;
import com.asamart.repository.ProductPriceRepository;
import com.asamart.service.ProductPriceService;
@Service
public class ProductPriceImpServicelementation implements ProductPriceService {

	/* @Author: Sumit Gaikwad */
	@Autowired
	private ProductPriceRepository productPriceRepository;

	@Override
	public ProductPrice savePriceDetails(ProductPrice priceDetails) {
		return productPriceRepository.save(priceDetails);
	}

}
