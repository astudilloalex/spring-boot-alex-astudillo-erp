package com.alexastudillo.erp.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexastudillo.erp.security.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(final String username);
}
