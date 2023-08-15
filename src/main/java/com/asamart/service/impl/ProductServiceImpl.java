package com.asamart.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.repository.ProductRepository;
import com.asamart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void deleteProduct(Integer id) {
		logger.info("in ProductService class delete method");
		productRepository.deleteById(id);
	}

}
