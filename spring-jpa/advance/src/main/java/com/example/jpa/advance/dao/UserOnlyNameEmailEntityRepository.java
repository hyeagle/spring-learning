package com.example.jpa.advance.dao;

import com.example.jpa.advance.model.UserOnlyNameEmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOnlyNameEmailEntityRepository extends JpaRepository<UserOnlyNameEmailEntity, Long> {
}
