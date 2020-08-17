package com.project.webboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {    //공지: 번호, 제목, 날짜, 내용

    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    private String title;

    private String content;


}
