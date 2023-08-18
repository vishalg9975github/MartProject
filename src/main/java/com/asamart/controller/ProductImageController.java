package com.asamart.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asamart.exceptions.ProductImageNotFoundException;
import com.asamart.exceptions.ProductImageUpdateException;
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
	public ProductImage addProductImage(@RequestParam("image") MultipartFile image,
			@RequestParam("productid") int productid, @RequestParam("defaultImage") boolean defaultImage)
			throws IOException {
		final String UPLOAD_DIR = "E:/velocityProject/image";
		String imageFileName = image.getOriginalFilename();
		String path = UPLOAD_DIR + File.separator + imageFileName;
		logger.info("In productImage controller class,addproductImage");
		Path targetPath = Path.of(path);
		Files.copy(image.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

		ProductImage productImage = new ProductImage();
		productImage.setImagePath(path);
		productImage.setProductid(productid);
		productImage.setDefaultImage(defaultImage);
		return productImageService.addProductImage(productImage);

	}

	/* @Auther shiwani dewang */
	@PutMapping("/update/{imageId}")
	public ResponseEntity<Map<String, Object>> updateProductImageDetails(@PathVariable int imageId,
			@RequestParam("productid") int productid, @RequestParam("defaultImage") boolean defaultImage,
			@RequestParam(value = "image", required = false) MultipartFile image) {
		logger.info("In productImageController class, update productImage method");
		try {
			ProductImage product = productImageService.updateProductImage(imageId, productid, defaultImage, image);
			Map<String, Object> response = new HashMap<>();
			response.put("productImage", product);
			response.put("message", "ProductImage updated successfully.");
			return ResponseEntity.ok(response);
		} catch (ProductImageNotFoundException ex) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", ex.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		} catch (ProductImageUpdateException ex) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		} catch (Exception ex) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "An error occurred");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	// @Author- Sachin More

	@DeleteMapping("/deleteProductImage/{imageId}")

	public void deleteProductImageById(@PathVariable("imageId") Integer imageId) {
		logger.info("in productImagecontroller class deletemapping");
		productImageService.deleteProductImage(imageId);
		// System.out.println("product deleted successfully");

	}

}
