package com.durgesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
