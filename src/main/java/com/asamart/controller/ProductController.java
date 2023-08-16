package com.asamart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.model.Product;
import com.asamart.service.ProductService;

@RestController
public class ProductController {

		private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
		@Autowired
		private ProductService productService; 
		
		/*Author name - Nandini Borase*/
		
		// Design the Restful web services to save the product data into database.
		@PostMapping("/saveProduct")
		public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
			Product pro = productService.saveProduct(product); 
			logger.info("In the Controller class,saveProduct method");
			return ResponseEntity.ok().body(pro); 
		}
}
