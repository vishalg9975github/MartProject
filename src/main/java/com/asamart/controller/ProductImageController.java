package com.asamart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.model.ProductImage;
import com.asamart.service.ProductImageService;

@RestController
public class ProductImageController {

	@Autowired
	private ProductImageService productImageService;
	
	//@Author Swapnil Gawai
    @GetMapping("/images")
	public List<ProductImage> getAllImages() {

		List<ProductImage> getAllImages = productImageService.getAllImages();

		return getAllImages;

	}
}
