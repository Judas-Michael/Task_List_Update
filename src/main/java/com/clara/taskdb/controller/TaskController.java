package com.clara.taskdb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

    @GetMapping("/")
    public String homePage(){
        return "index.html"; //returns index of homepage
    }
}

