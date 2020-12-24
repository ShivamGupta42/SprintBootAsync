package com.springboot.async.springBootAsync.repository;


import com.springboot.async.springBootAsync.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}