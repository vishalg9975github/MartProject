package com.asamart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asamart.model.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer >{
	
	@Query(value="select * from productimage where default_image='0' and image_id=?1", nativeQuery = true)
	
	public ProductImage getProductImageAndNotDeleted(int id);


	boolean existsByImageHash(String imageHash);

}

