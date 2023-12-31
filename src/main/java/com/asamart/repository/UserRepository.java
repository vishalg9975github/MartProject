package com.asamart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asamart.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByName(String name);

	public Optional<User> findByEmail(String email);

	public Optional<User> findByPassword(String password);
}
