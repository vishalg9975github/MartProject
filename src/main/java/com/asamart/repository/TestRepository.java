package com.asamart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asamart.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, String> {

}
