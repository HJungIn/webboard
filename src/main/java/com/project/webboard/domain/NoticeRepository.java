package com.project.webboard.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository  extends JpaRepository<Notice, Long> {

    @Override
    List<Notice> findAll();

    @Override
    Optional<Notice> findById(Long id);

//    @Query("select n from Notice n where n.id = :id")
//    Notice findById(@Param("id") Long id);
}

