package com.asamart.service;

import java.util.List;

import com.asamart.model.ProductPrice;

public interface ProductPriceService {


	public ProductPrice savePriceDetails(ProductPrice priceDetails);

	public ProductPrice getProductPrice(Integer id);

	// @Younus K Shaikh
	public ProductPrice updateProductPriceById(int id, ProductPrice productPrice);
	
	public List<ProductPrice> getAllDetails();




//delete by id
	String softDeleteById(Integer id);
	

}
