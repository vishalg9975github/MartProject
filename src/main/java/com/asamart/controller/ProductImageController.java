package com.asamart.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asamart.model.ProductImage;
import com.asamart.service.ProductImageService;

@RestController
@RequestMapping("/Image")
public class ProductImageController {
	private static final Logger logger = LoggerFactory.getLogger(ProductImageController.class);
	@Autowired
	private ProductImageService productImageService;

	/* @Author -shiwani dewang */
	@PostMapping("/addProductImage")
	public ResponseEntity<ProductImage> addProductImage(@ModelAttribute ProductImage productImage,
			@RequestParam("image") MultipartFile image) throws IOException {
		logger.debug("In ProductImageController class,addProductImage method");
		ProductImage addedImage = productImageService.addProductImage(productImage, image);
		return ResponseEntity.ok(addedImage);

	}

	/* @Auther shiwani dewang */
	@PutMapping("/update/{imageId}")
	public ResponseEntity<ProductImage> updateProductImage(@PathVariable int imageId,
			@ModelAttribute ProductImage updatedImage,
			@RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
		logger.debug("In ProductImageController class,updateProductImage method");
		ProductImage updated = productImageService.updateProductImage(imageId, updatedImage, image);
		return ResponseEntity.ok(updated);

	}


	//@Author Swapnil Gawai
   // @GetMapping("/images")
	/* @Auther Anushka */
	
	@GetMapping("/getProductImageById/{imageId}")
	public ResponseEntity<ProductImage> getProductImageById(@PathVariable("imageId") int imageId) {
		logger.info("In ProductImageController Class , getProductImageById");
		
		ProductImage productImage= productImageService.getProductImageById(imageId);
		return ResponseEntity.ok(productImage);
	}
	


	

	// @Author Swapnil Gawai
	@GetMapping("/images")
	public List<ProductImage> getAllImages() {

		List<ProductImage> getAllImages = productImageService.getAllImages();

		return getAllImages;

	}

	// @Author - sachin more
	@DeleteMapping("/DeleteProductImage/{id}")
	public ResponseEntity<String> softDeleteProduct(@PathVariable Integer id) {
		
		productImageService.softDeleteProduct(id);

		return ResponseEntity.ok("Product deleted successfully");
	}

	

}

