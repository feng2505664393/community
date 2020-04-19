package com.example.demo.controller;

import com.example.demo.dto.PaginationDTO;
import com.example.demo.model.User;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action", value = "") String action,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "size",defaultValue = "10")Integer size,
                          HttpServletRequest httpServletRequest) {
        //登录验证，获取User
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        //未登录，返回首页
        if(user==null) return "redirect:/";
        //已经登录，展示问题页
        if ("questions".equals(action)) {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        //根据User返回用户的文章
        PaginationDTO paginationDTO = questionService.list(user.getId(),page,size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "profile";
    }
}
