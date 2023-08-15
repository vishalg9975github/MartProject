package com.asamart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.model.Product;
import com.asamart.service.ProductService;

@RestController
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@PutMapping("/updateProductById/{id}")
	public ResponseEntity<Product> updateProductById(@PathVariable("id") int id, @RequestBody Product product) {
		Product product2 = productService.updateProductById(id, product);
		return ResponseEntity.ok().body(product2);

	}
}
