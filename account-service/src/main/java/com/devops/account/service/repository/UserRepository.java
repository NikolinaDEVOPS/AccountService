package com.devops.account.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devops.account.service.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
