package com.example.jpa.entity.repository;

import com.example.jpa.entity.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
