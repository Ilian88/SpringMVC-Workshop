package com.example.judgev2_alone.model.bindingModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    private String username;

    private String password;

    public UserLoginBindingModel() {
    }

    @NotNull
    @Size(min = 2,message = "Size must be at least 2 characters long")
    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Size(min = 3,message = "Password must be at least 3 characters long")
    @NotNull
    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
