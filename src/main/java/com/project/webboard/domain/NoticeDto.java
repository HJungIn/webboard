package com.project.webboard.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;


public class NoticeDto {
    private Long id;
    private String title;
    private String content;

    public NoticeDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
