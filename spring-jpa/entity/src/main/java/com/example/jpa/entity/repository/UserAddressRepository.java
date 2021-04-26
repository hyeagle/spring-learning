package com.example.jpa.entity.repository;

import com.example.jpa.entity.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
