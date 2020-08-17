package com.project.webboard.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> getAllNotices(){
        return noticeRepository.findAll();
    }

    public Optional<Notice> getNoticeById(Long id){
        return noticeRepository.findById(id);
    }
}
