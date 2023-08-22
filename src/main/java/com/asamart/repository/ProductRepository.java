package com.asamart.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asamart.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query(value="select * from product where productname=?1", nativeQuery = true)

	public String findByProductNmae(String bname);
}
