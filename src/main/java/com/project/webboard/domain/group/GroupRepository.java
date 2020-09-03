package com.project.webboard.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from Group g where g.id = :id")
    Group findGroupById(@Param("id") Long id);

    @Query("select g from Group g where g.name like concat('%',:groupname,'%') ")
    List<Group> findGroupsByName(@Param("groupname") String groupname);
}
