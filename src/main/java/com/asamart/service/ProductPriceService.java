package com.asamart.service;

import com.asamart.model.ProductPrice;

public interface ProductPriceService {
	public ProductPrice savePriceDetails(ProductPrice priceDetails);

	// @Younus Shaikh
	public ProductPrice updateProductPriceById(int id, ProductPrice productPrice);

}
