package com.example.jpa.advance.dao;

import com.example.jpa.advance.model.User;
import com.example.jpa.advance.model.UserCount;
import com.example.jpa.advance.model.UserDto;
import com.example.jpa.advance.model.UserInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<UserDto> findByName(String name);

    List<UserInterface> findByAddress(String address);

    @Query("From User where name=:name")
    List<User> findByQuery(@Param("name") String name);

    @Query("From User where name like %?1") // 需要手动加上 %
    List<User> findByLike(@Param("name") String name);

    // native 不支持 Sort
    @Query(value = "select * from user where name=?1 order by ?2", nativeQuery = true)
    List<User> findByNative(String name, String field);

    @Query(value = "select u From User u where u.name=:name")
    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "select name, count(*) countNum from user group by name", nativeQuery = true)
    List<UserCount> groupCount();

    @Query(value = "select name, max(id) maxId from user group by name", nativeQuery = true)
    List<UserCount> groupMax();
}
