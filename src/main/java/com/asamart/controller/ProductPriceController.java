package com.asamart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.model.Product;
import com.asamart.model.ProductPrice;
import com.asamart.service.ProductPriceService;

@RestController
@RequestMapping("/ProductPrice")
public class ProductPriceController {
	//@Younus Shaikh
	private static final Logger logger = LoggerFactory.getLogger(ProductPriceController.class);

	/* @Author:Sumit Gaikwad */
	@Autowired
	private ProductPriceService productPriceService;

	@PostMapping("/savePriceDetails")
	public ResponseEntity<ProductPrice> savePriceDetails(@RequestBody ProductPrice productPrice) {
		ProductPrice priceDetails = productPriceService.savePriceDetails(productPrice);
		return ResponseEntity.ok().body(priceDetails);	

	}
	
	//@auther - Younus K Shaikh
	@PutMapping("/updateProductPriceById/{id}")
	public ResponseEntity<ProductPrice> updateProductPriceById(@PathVariable("id") int id,
			@RequestBody ProductPrice productPrice) {
		logger.info("Update the Records");
		ProductPrice productPrice2 = productPriceService.updateProductPriceById(id, productPrice);
		return ResponseEntity.ok().body(productPrice2);

	}
	/* Author - Nandini Borase*/
	@GetMapping("/allProductPrice")
	public List<ProductPrice> getAllDetails() {
		List<ProductPrice> allProductPriceDetails = productPriceService.getAllDetails();
		List<ProductPrice> filteredList = new ArrayList<ProductPrice>();
		for (ProductPrice productPrice :allProductPriceDetails) {
			if(productPrice.isDeleted() == false) {
				filteredList.add(productPrice);
			}
		}
		if(filteredList.isEmpty()==true) {
			throw new EmptyResultDataAccessException(0);
		}
		logger.info("get all the Records");
		return filteredList;

	}
 /* @Author: Suraj Wankhede */
	
	@GetMapping("/getPriceDetails/{Id}")
	public ResponseEntity<ProductPrice> getstudentbyId(@PathVariable("Id") Integer id)
	{
		
		ProductPrice p1=productPriceService.getProductPrice(id);
		
		if(p1.isDeleted()==true){
			throw new NoSuchElementException();
		}
		return ResponseEntity.ok().body(p1);

		

	}
	
	/* @Author:Nilesh D. L. */
	@DeleteMapping("/deleteProductPrice/{id}")
	public String softDeleteById(@PathVariable Integer id) {

	return	productPriceService.softDeleteById(id);
	}
	
	
	
	
}
