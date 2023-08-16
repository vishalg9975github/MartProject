package com.asamart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.controller.ProductController;
import com.asamart.exceptions.CustomeExceptions;
import com.asamart.model.Product;
import com.asamart.repository.ProductRepository;
import com.asamart.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;
	
	// @ Author -Nandini
	@Override
	public Product saveProduct(Product pd) {
		logger.info("In the Controller class,saveProduct method");
		return productRepository.save(pd);
	}

	// @ Author -Anushka

	// Update the product details by using id

	@Override
	public Product updateProductById(int id, Product product) {
		logger.info("Update the product details by Id");

		int pid = product.getProductid();
		pid = id;
		Product product2 = new Product();
		try {

			product2 = productRepository.findById(pid).get();
			product2.setBrand(product.getBrand());
			product2.setFeatured(false);
			product2.setProductcode(product.getProductcode());
			product2.setProductdescription(product2.getProductdescription());
			product2.setProductname(product.getProductname());
			product2.setTags(product.getTags());

		} catch (Exception e) {
			throw new CustomeExceptions("Product id " + id + " is incorrect..");
		}
		return product2;
	}

	@Override
	public void deleteProduct(Integer id) {
		logger.info("in ProductService class delete method");
		productRepository.deleteById(id);
		
	}

	//Get Product details by using Id
	@Override
	public Product getProductById(Integer Id) {
		logger.info("In ProductServiceImpl , getProduct Data");
		Product product = productRepository.findById(Id).get();
		return product;
	}

}