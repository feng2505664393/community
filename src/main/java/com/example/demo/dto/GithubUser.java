package com.example.demo.dto;

public class GithubUser {
    private Long id;
    private String bio;
    private String login;

    public String getName() {
        return login;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
