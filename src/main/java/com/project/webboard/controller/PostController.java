package com.project.webboard.controller;

import com.project.webboard.config.oauth.SessionUser;
import com.project.webboard.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final GroupService groupService;

    private final HttpSession httpSession;

    @RequestMapping("/mygroups/{groupid}/post/{postid}")
    private String detailPost(Model model, @PathVariable("groupid") Long groupid,
                              @PathVariable("postid") Long postid ){

        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        Post post = postService.getPost(postid);
        if(post.getPublisher().getEmail().equals(user.getEmail()))
            model.addAttribute("samePublisher", true);

        model.addAttribute("groupid", groupid);
        model.addAttribute("postById", post);

        return "detailpost";
    }

    @RequestMapping("/mygroups/{groupid}/makepost")
    private String makePost(Model model, @PathVariable("groupid") Long groupid){

        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        model.addAttribute("groupid", groupid);

        return "makepost";
    }

    @RequestMapping("/mygroups/{groupid}/makepostsubmit")
    private String makePostSubmit(@PathVariable("groupid") Long groupid,
                                  @RequestParam("title") String title,
                                  @RequestParam("content") String content){

        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        User currentuser = userService.getUser(user.getEmail());
        Group currentgroup = groupService.getGroup(groupid);

        Post post = new Post(title, content, currentuser, currentgroup);
        postService.savePost(post);

        return "redirect:/mygroups/{groupid}";
    }

    @RequestMapping("/mygroups/{groupid}/deletepost/{postid}")
    private String deletePost(Model model,
                              @PathVariable("groupid") Long groupid,
                              @PathVariable("postid") Long postid){

        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        Post post = postService.getPost(postid);
        postService.removePost(post);

        return "redirect:/mygroups/{groupid}";
    }
}
