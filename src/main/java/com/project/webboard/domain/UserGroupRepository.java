package com.project.webboard.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("select distinct ug.group from UserGroup ug where ug.user = :user")
    List<Group> findByUser(@Param("user") User user);
}
