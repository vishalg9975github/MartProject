package com.asamart.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

}
