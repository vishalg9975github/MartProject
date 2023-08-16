package com.asamart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.controller.ProductController;
import com.asamart.controller.TestController;
import com.asamart.model.Product;
import com.asamart.repository.ProductRepository;
import com.asamart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product saveProduct(Product pd) {
		logger.info("In the Controller class,saveProduct method");
		return productRepository.save(pd);
	}

}
