package com.example.judgev2_alone.model.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;

    private String password;

    private String email;

    private String git;

    private Role role;

    private List<Homework> homeworks;

    public User() {
    }

    @Column(name = "username",nullable = false,unique = true)
    @Size(min = 2)
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "password",nullable = false)
    @Size(min = 3)
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "email",unique = true)
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "git")
    public String getGit() {
        return git;
    }

    public User setGit(String git) {
        this.git = git;
        return this;
    }


    @ManyToOne
    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    @OneToMany(mappedBy = "author")
    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public User setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
        return this;
    }
}
