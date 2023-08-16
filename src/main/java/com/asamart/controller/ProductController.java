package com.asamart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	/*Author name - Nandini Borase*/

	// Design the Restful web services to save the product data into database.
	@PostMapping("/saveProduct")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product pro = productService.saveProduct(product); 
		logger.info("In the Controller class,saveProduct method");
		return ResponseEntity.ok().body(pro); 
	}



	// @Author- Anushka

	@PutMapping("/updateProductById/{id}")
	public ResponseEntity<Product> updateProductById(@PathVariable("id") int id, @RequestBody Product product) {
		logger.info("Update the Records");
		Product product2 = productService.updateProductById(id, product);
		return ResponseEntity.ok().body(product2);

	}

	// @Author- Sachin More

	@DeleteMapping("/deleteProduct/{id}")
	public void deleteProductById(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		logger.info("in productcontroller class deletemapping");
	}

	// @Auther - Younus Shaikh

	@GetMapping("/getProduct/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		Product pro = productService.getProductById(id);
		logger.info("In Rest Contoller , get Product data");
		return ResponseEntity.ok().body(pro);

	}

}