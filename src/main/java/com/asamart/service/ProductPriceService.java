package com.asamart.service;

import com.asamart.model.ProductPrice;

public interface ProductPriceService {
public ProductPrice savePriceDetails(ProductPrice priceDetails);
public ProductPrice getProductPrice(Integer id);

}
