package com.asamart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.service.ProductService;
import org.slf4j.Logger;

@RestController
public class ProductController {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@DeleteMapping("/deleteProduct/{id}")
	public void deleteProductById(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		logger.info("in productcontroller class deletemapping");
	}

}
