package com.project.webboard.controller;

import com.project.webboard.api.ApiSearchBook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BookController {

    @RequestMapping("/searchbook")
    public String searchBook(){
        return "searchbook";
    }

    @RequestMapping("/mygroups/{groupid}/searchbookSubmit")
    public String searchBookSubmit(Model model,
                                   @PathVariable("groupid") Long groupid,
                                   @RequestParam("title")String title){

        model.addAttribute("groupid", groupid);

        ApiSearchBook apiSearchBook = new ApiSearchBook();
        model.addAttribute("books",apiSearchBook.searchBook(title));

//        System.out.println("bookcontroller- searchBookSubmit : jsonArrayList = " + apiSearchBook.searchBook(title));

        return "makepost";
    }

}
