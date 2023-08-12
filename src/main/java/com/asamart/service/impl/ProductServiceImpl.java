package com.asamart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.controller.ProductController;
import com.asamart.model.Product;
import com.asamart.repository.ProductRepository;
import com.asamart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	
	// @ Author -Anushka
	
	// Update the product details by using id
	
	@Override
	public Product updateProduct(Product product) {
		logger.info("Update the product by Id");
		int pid = product.getProductid();
		Product product2 = productRepository.findById(pid).get();
		product2.setBrand(product.getBrand());
		product2.setFeatured(false);
		product2.setProductcode(product.getProductcode());
		product2.setProductdescription(product2.getProductdescription());
		product2.setProductname(product.getProductname());
		product2.setTags(product.getTags());
		return product2;
	}

}
