package com.asamart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asamart.model.Product;

@Repository

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "select * from product where productname=?1", nativeQuery = true)

	public String findByProductByName(String productname);

	Product findByproductname(String productname);


	@Query(value = "select * from product where is_deleted='0' and productid=?1", nativeQuery = true)

//	public Object findByproductname1(String productname);



	public Product findProductByNameAndId(int id);

//for images
	Product findByproductid(Integer productid);
}
