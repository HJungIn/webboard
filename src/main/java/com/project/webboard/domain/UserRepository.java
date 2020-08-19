package com.project.webboard.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); //customOauth2userservice에서 필요

    @Query("select u from User u where u.email = :email")
    User findUserByEmail(@Param("email") String email); //userservice에서 필요


}
