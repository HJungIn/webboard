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
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class GroupController {

    private final UserService userService;
    private final GroupService groupService;
    private final UserGroupService userGroupService;

    private final HttpSession httpSession;

    @RequestMapping("/makegroup")
    public String makeGroup(Model model){
        SessionUser user = (SessionUser)httpSession.getAttribute("user");
        if(user!= null) {
            model.addAttribute("currentUser", user);
        }

        return "makegroup";
    }

    @RequestMapping("/makegroupsubmit")
    public String makeGroupSubmit(@RequestParam("groupName") String groupName, Model model){

        SessionUser user = (SessionUser)httpSession.getAttribute("user");
        if(user!= null) {
            model.addAttribute("currentUser", user);
        }

        //사용자 찾기
        User owner = userService.getUser(user.getEmail());

        //그룹 생성
        Group group = new Group(groupName, owner);
        groupService.saveGroup(group);

        //다대다 저장
        userGroupService.saveUserGroup(new UserGroup(owner, group));

        //연관관계메서드
        System.out.println("했음");

        return "redirect:/home";
    }

    @RequestMapping("/mygroups")
    public String myGroups(Model model){
        SessionUser user = (SessionUser)httpSession.getAttribute("user");
        if(user!= null) {
            model.addAttribute("currentUser", user);
        }
        //사용자 찾기
        User currentUser = userService.getUser(user.getEmail());

        //내가 속한 그룹애들 가져오기
        model.addAttribute("mygroupslist",userGroupService.getUserGroup(currentUser));



        return "mygroups";
    }

    @RequestMapping("/mygroups/{idx}")
    public String groupMainPage(Model model, @PathVariable("idx") Long id){

        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        Group group = groupService.getGroup(id);
        model.addAttribute("group", group);

        model.addAttribute("posts", group.getPosts());

        return "groupmainpage";
    }
}