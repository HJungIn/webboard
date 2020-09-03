package com.project.webboard.controller;

import com.project.webboard.api.ApiSearchBook;
import com.project.webboard.config.oauth.SessionUser;
import com.project.webboard.domain.group.Group;
import com.project.webboard.domain.group.GroupService;
import com.project.webboard.domain.post.Post;
import com.project.webboard.domain.post.PostService;
import com.project.webboard.domain.user.User;
import com.project.webboard.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final GroupService groupService;

    private final HttpSession httpSession;

    @RequestMapping(value = {"/mygroups/{groupid}/post/{postid}", "/groups/{groupid}/post/{postid}"})
    private String detailPost(Model model,
                              @PathVariable("groupid") Long groupid,
                              @PathVariable("postid") Long postid ){

        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        Post post = postService.getPost(postid);
        if(user != null && post.getPublisher().getEmail().equals(user.getEmail()))
            model.addAttribute("samePublisher", true);

        model.addAttribute("groupid", groupid);
        model.addAttribute("postById", post);

        if(post.getBooks().size()==0)
            return "detailpost";


        ApiSearchBook apiSearchBook = new ApiSearchBook();
        List<String> books = post.getBooks();
        JSONArray jsonArrayList = apiSearchBook.searchBook(books.get(0));

        for(int i=1;i<books.size();i++){
//            System.out.println("book = " + books.get(i));
            jsonArrayList.merge(apiSearchBook.searchBook(books.get(i)));
        }

        model.addAttribute("jsonbooks", jsonArrayList);
//        System.out.println("                          detail : jsonArrayList = " + jsonArrayList);

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
                                  @RequestParam("content") String content,
                                  @RequestParam("checkbook")List<String> checkbook){

        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        User currentuser = userService.getUser(user.getEmail());
        Group currentgroup = groupService.getGroup(groupid);

        Post post = new Post(title, content, currentuser, currentgroup, checkbook);
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
