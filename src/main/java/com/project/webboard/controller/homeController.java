package com.project.webboard.controller;

import com.project.webboard.config.oauth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class homeController {

    private final HttpSession httpSession;

    @RequestMapping("/home")
    public String home(Model model){

        SessionUser user = (SessionUser)httpSession.getAttribute("user");
        if(user!= null) {
            model.addAttribute("what", user.getName());
        }

        return "home";
    }

}

