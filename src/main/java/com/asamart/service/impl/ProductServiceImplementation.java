package com.asamart.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;

import com.asamart.controller.ProductController;
import com.asamart.model.Product;

import com.asamart.model.ProductImage;
import com.asamart.repository.ProductImageRepository;

import com.asamart.repository.ProductRepository;
import com.asamart.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductImageRepository productImageRepository;
	@Value("${UPLOAD_DIR}")
	private String uploadDirectory;
	@Autowired
	private ProductImageServiceImplementation productImageServiceImplementation;

	/* @Author Ankita Ghayal */
	@Override
	public List<Product> getProduct() {
		List<Product> productList = new ArrayList<>();
		productList = productRepository.findAll();
		logger.info("In product controller get ProductList method");
		return productList;
	}

	// @ Author -Nandini
	/*
	@Override

	public Product saveProduct(Product pd) {
		logger.info("In the Controller class,saveProduct method");

		if (productRepository.findByProductNmae(pd.getProductname()) != null) {
			return productRepository.save(pd);

		}

		return productRepository.save(pd);
*/
	
	public Product saveProduct(Product product) {
logger.info("In the Controller class,saveProduct method");

		String productname= product.getProductname();
		
		if(productRepository.findByProductByName(productname) != null) 
		{
			throw new EntityNotFoundException("Product with the same name already exists: " + productname);
		}
		
			return productRepository.save(product);
		
	}

	// @ Author -Anushka
	// Update the product details by using id
	@Override
	public Product updateProductById(int id, Product product) {
		logger.info("Update the product details by Id");

		int pid = product.getProductid();
		pid = id;

		Product product2 = productRepository.findById(pid).get();

		//Product product2 = productRepository.findProductByNameAndId( id);

		//int pid = product.getProductid();
		

		product2.setBrand(product.getBrand());
		product2.setFeatured(false);
		product2.setProductcode(product.getProductcode());
		product2.setProductdescription(product2.getProductdescription());
		product2.setProductname(product.getProductname());
		product2.setTags(product.getTags());

		return productRepository.save(product2);

	}

	

	// Get Product details by using Id
	// Auther - Younus K Shaikh
	@Override
	public Product getProductById(Integer Id) {
		logger.info("In ProductServiceImpl , getProduct Data");
		Product product = productRepository.findById(Id).get();
		return product;
	}

	// Author sachin more
	// soft delete in db
	@Transactional
	public void softDeleteProduct(Integer productId) {
		Product product = productRepository.findById(productId).orElse(null);
		if (product != null) {
			product.setDeleted(true);
			productRepository.save(product);
		}
	}

	/* @author-shiwani dewang */
	private final Set<String> processedImageNames = new HashSet<>();

	@Override
	@Transactional
	public void saveProductWithImages(String productname, String productdescription, String brand, String tags,
			String productcode, boolean featured, List<MultipartFile> images) throws Exception {
		if (productRepository.findByproductname(productname) != null) {
			throw new Exception("Product with the  name " + productname + "already exists");
		}
		logger.info("ProductService Implementation class , save product with image method");
		Product product = new Product();
		product.setProductname(productname);
		product.setProductdescription(productdescription);
		product.setBrand(brand);
		product.setTags(tags);
		product.setProductcode(productcode);
		product.setFeatured(featured);
		product = productRepository.save(product);
		for (MultipartFile image : images) {
			byte[] imageBytes = image.getBytes();
			String imageHash = calculateImageHash(imageBytes);
			String imageName = image.getOriginalFilename();

			if (productImageServiceImplementation.imageExistsInDatabase(imageHash)) {
				throw new Exception(
						"Duplicate image detected please rename the image !! " + image.getOriginalFilename());
			}

			saveImageToFolderAndDatabase(imageBytes, imageName, imageHash, product);
		}
	}

	private String calculateImageHash(byte[] imageBytes) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = md.digest(imageBytes);

			StringBuilder hexString = new StringBuilder();
			for (byte hashByte : hashBytes) {
				String hex = Integer.toHexString(0xff & hashByte);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error calculating image hash.", e);
		}
	}

	private void saveImageToFolderAndDatabase(byte[] imageBytes, String imageName, String imageHash, Product product) {
		String imagePath = saveImageToFolder(imageBytes, imageHash, imageName);

		ProductImage productImage = new ProductImage();
		productImage.setImageHash(imageHash);
		productImage.setImagePath(imagePath);
		productImage.setDefaultImage(false);
		productImage.setImageName(imageName);
		productImage.setProduct(product);
		productImageServiceImplementation.saveProductImage(productImage);
	}

	private String saveImageToFolder(byte[] imageBytes, String imageHash, String imageName) {
		// Logic to save the image to the images folder
		String imagePath = "src/main/resources/images/" + imageHash + ".jpg"; // Change to your actual path

		try {
			Files.write(Paths.get(imagePath), imageBytes);
			return imagePath;
		} catch (IOException e) {
			throw new RuntimeException("Error saving image.", e);
		}
	}
}
