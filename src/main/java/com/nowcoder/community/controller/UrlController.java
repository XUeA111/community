package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UrlController {

    @GetMapping("index2")
    public String getIndex(Model model){
        model.addAttribute("name", "never give up");
        return "index2";
    }

}
