package com.asamart.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.model.Product;
import com.asamart.model.SubCategory;
import com.asamart.service.ProductService;

@RestController
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	/* @Author Ankita Ghayal */
	//Design the restful web service to get all productList details into database
	@GetMapping("getProductList")
	public ResponseEntity<List<Product>> getProductList(){
		List<Product> productList = productService.getProduct();
		logger.info("In product controller get ProductList method");
		return ResponseEntity.ok().body(productList);
	}	

	//@Author- Anushka
	@PutMapping("/updateProductById/{id}")
	public ResponseEntity<Product> updateProductById(@PathVariable("id") int id, @RequestBody Product product) {
		logger.info("Update the Records");
		Product product2 = productService.updateProductById(id, product);
		return ResponseEntity.ok().body(product2);

	}
	
	//@Author- Sachin More

	@DeleteMapping("/deleteProduct/{id}")
	public void deleteProductById(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		logger.info("in productcontroller class deletemapping");
	}


}
