package com.project.webboard.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.id = :id")
    Post findPostById(@Param("id") Long id);

}
