package com.example.jpa.entity.repository;

import com.example.jpa.entity.model.Gender;
import com.example.jpa.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    
    List<User> findByGender(Gender gender);
}
