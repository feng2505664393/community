package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse){
        AccessTokenDTO atd = new AccessTokenDTO();
        atd.setCode(code);
        atd.setState(state);
        atd.setClient_id("f9f84b606a3908150f0d");
        atd.setClient_secret("4b439fb6961753c43dc8663348b5567646614b81");
        atd.setRedirect_url("http://localhost:8887/callback");

        String accessToken = githubProvider.getAccessToken(atd);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null){
            //登陆成功
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            httpServletResponse.addCookie(new Cookie("token",token));

            userMapper.insert(user);
            httpServletRequest.getSession().setAttribute("user",githubUser);
            System.out.println("登录成功");
        }else{
            //登录失败
            System.out.println("登录失败");
        }
        return "redirect:/";
    }
}
