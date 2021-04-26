package com.example.jpa.base.dao;

import com.example.jpa.base.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);

    // 可以返回 Page/Slice，page 会查询总数，slice 只查询偏移量，不计算分页数据
    List<User> findByName(String name, Pageable page);
}
