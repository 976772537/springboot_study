package com.web.spider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("spider")
    public String spider(){
        return "/spider";
    }
}
