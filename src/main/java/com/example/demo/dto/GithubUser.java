package com.example.demo.dto;

public class GithubUser {
    private Long id;
    private String bio;
    private String login;
    private String avatarURL;

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getName() {
        return login;   //名字这里有点小问题
    }

    public void setLogin(String name) {
        this.login = name;   //注意这里的小问题
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
