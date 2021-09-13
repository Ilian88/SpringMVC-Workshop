package com.example.judgev2_alone.model.view;

import java.util.List;

public class ProfileViewModel {

    private String username;

    private List<String> homeworks;

    private String email;

    private String git;

    public ProfileViewModel() {
    }



    public List<String> getHomeworks() {
        return homeworks;
    }

    public ProfileViewModel setHomeworks(List<String> homeworks) {
        this.homeworks = homeworks;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGit() {
        return git;
    }

    public ProfileViewModel setGit(String git) {
        this.git = git;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ProfileViewModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
