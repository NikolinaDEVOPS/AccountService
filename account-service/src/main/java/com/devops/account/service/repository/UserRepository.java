package com.devops.account.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devops.account.service.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByIsPublicTrue();

	Optional<User> findByUsername(String username);
}
