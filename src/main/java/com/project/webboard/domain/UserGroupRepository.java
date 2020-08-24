package com.project.webboard.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("select distinct ug.group from UserGroup ug where ug.user = :user")
    List<Group> findByUser(@Param("user") User user);

    @Query("select ug from UserGroup ug where ug.user = :user and ug.group = :group")
    UserGroup findByUserAndGroup(@Param("user")User user, @Param("group") Group group);
}
