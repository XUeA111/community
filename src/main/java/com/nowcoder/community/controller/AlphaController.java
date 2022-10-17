package com.nowcoder.community.controller;

import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }

    @RequestMapping(path = "/student", method = RequestMethod.GET)
    public String getStudent(Model model){
        model.addAttribute("name", "zhang3");
        model.addAttribute("age", 10);
        return "/demo/view";
    }

    @RequestMapping(path = "/student2", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent2(String nameStu, int grade){
        System.out.println("Student's name: " + nameStu);
        System.out.println("Student's grade: " + grade);
        return "Never give up";
    }

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "xu");
        emp.put("addr", 1);
        return emp;
    }

}
