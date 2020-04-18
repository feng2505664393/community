package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUser;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO atd = new AccessTokenDTO();
        atd.setCode(code);
        atd.setState(state);
        atd.setClient_id("f9f84b606a3908150f0d");
        atd.setClient_secret("4b439fb6961753c43dc8663348b5567646614b81");
        atd.setRedirect_url("http://localhost:8887/callback");

        String accessToken = githubProvider.getAccessToken(atd);
        GithubUser user = githubProvider.getUser(accessToken);

        //System.out.println(user.getName());
        System.out.println(user.getLogin());
        return "index";
    }
}
