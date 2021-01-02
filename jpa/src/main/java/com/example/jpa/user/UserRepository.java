package com.example.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findByEmailAndName(String email, String name);

    @Query("select u from User u")
    Stream<User> findAllBy();

    UserOnlyNameAndEmail findByEmail(String email);

    @Query(value = "select u.name as name from User u")
    List<UserOnlyName> findOnlyName();

    @Query("select u from User u where (:#{#user.name} is null or u.name=:#{#user.name}) and (:#{#user.email} is null or u.email=:#{#user.email})")
    List<UserOnlyName> findDynamic(@Param("user") User user);

    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    //Optional<User> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User getOne(Long id);
}
