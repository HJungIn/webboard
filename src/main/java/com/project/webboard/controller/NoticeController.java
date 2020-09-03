package com.project.webboard.controller;

import com.project.webboard.domain.notice.Notice;
import com.project.webboard.domain.notice.NoticeDto;
import com.project.webboard.domain.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @RequestMapping("/notice")
    public String notice(Model model){

        List<Notice> allNotices = noticeService.getAllNotices();
        model.addAttribute("allNotices", allNotices);

        return "notice";
    }

    @RequestMapping("/notice/{idx}")
    public String detailNotice(Model model, @PathVariable("idx") Long id){

        Optional<Notice> noticeById = noticeService.getNoticeById(id);
        NoticeDto noticeDto = new NoticeDto(noticeById.get().getId(), noticeById.get().getTitle(), noticeById.get().getContent());

        model.addAttribute("noticeById",noticeDto);

        return "detailnotice";
    }


}
