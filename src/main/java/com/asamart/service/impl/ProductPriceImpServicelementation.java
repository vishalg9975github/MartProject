package com.asamart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
