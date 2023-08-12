package com.asamart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asamart.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
