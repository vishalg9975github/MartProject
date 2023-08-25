package com.asamart.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.Product;
import com.asamart.model.SubCategory;
import com.asamart.service.ProductService;

@RestController
@RequestMapping("/Product")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;

	/* @Author Ankita Ghayal */

	// Design the restful web service to get all productList details into database
	@GetMapping("/getProductList")
	public ResponseEntity<List<Product>> getProductList() {

		List<Product> productList = productService.getProduct();
		List<Product> filteredList = new ArrayList<Product>();
		for (Product product :productList) {
			if(product.isDeleted() == true) {
				filteredList.add(product);
			}
		}
		if(filteredList.isEmpty()==true) {
			throw new EmptyResultDataAccessException(0);
		}
		logger.info("In product controller get ProductList method");
		return ResponseEntity.ok().body(filteredList);
	}

	/* Author name - Nandini Borase */

	// Design the Restful web services to save the product data into database.
	@PostMapping("/saveProduct")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		logger.info("In the Controller class,saveProduct method");
		try {
		 productService.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	

	// @Author- Anushka

	@PutMapping("/updateProductById/{id}")
	public ResponseEntity<String> updateProductById(@PathVariable("id") int id, @RequestBody Product product) {
		logger.info("Update the Records");
		
			try {
				 productService.updateProductById(id, product);
				return ResponseEntity.status(HttpStatus.CREATED).body("Product Updated Successfully");
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Is Unavailable");
				}
		}

	// @Author- Sachin More

	@DeleteMapping("/deleteProduct/{id}")
	public void deleteProductById(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		logger.info("in productcontroller class deletemapping");

		// System.out.println("product deleted successfully");

	}

	// @Auther - Younus Shaikh

	@GetMapping("/getProduct/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		Product pro = productService.getProductById(id);
		logger.info("In Rest Contoller , get Product data");
		return ResponseEntity.ok().body(pro);

	}

	// @Author - sachin more
	@DeleteMapping("/softDeleteProduct/{id}")
	public ResponseEntity<String> softDeleteProduct(@PathVariable Integer id) {
		productService.softDeleteProduct(id);

		return ResponseEntity.ok("Product soft deleted successfully");
	}

	// @Author - sachin more
	@PostMapping("/recoverProduct/{id}")
	public ResponseEntity<String> recoverDeletedProduct(@PathVariable Integer id) {
		productService.recoverDeletedProduct(id);


		return ResponseEntity.ok("Product recovered successfully");
	}

	// to add the product with images
	// @author shiwani dewang
	@PostMapping("/addProductImage")
	public ResponseEntity<String> saveProductWithImages(@RequestParam String productname,
			@RequestParam String productdescription, @RequestParam String brand, @RequestParam String tags,
			@RequestParam String productcode, @RequestParam boolean featured,
			@RequestParam("images") List<MultipartFile> image) throws Exception {
		productService.saveProductWithImages(productname, productdescription, brand, tags, productcode, featured,
				image);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body("Product with images added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

}
