package com.asamart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.model.ProductImage;
import com.asamart.repository.ProductImageRepository;
import com.asamart.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	private ProductImageRepository productImageRepository;

	@Override
	public List<ProductImage> getAllImages() {
		
		return productImageRepository.findAll() ;
	}

	
	

}


