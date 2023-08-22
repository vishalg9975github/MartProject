package com.asamart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asamart.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findByIsDeletedFalse();

	Category findBycategoryname(String categoryname);

	Category findByImageHash(String imageHash);

	Category findByIdAndIsDeleted(Integer id, boolean isDeleted);

}
