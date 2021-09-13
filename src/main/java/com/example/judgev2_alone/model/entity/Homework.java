package com.example.judgev2_alone.model.entity;

import jdk.jfr.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "homework")
public class Homework extends BaseEntity{

    private LocalDateTime addedOn;

    private String gitAddress;

    private User author;

    private Exercise exercise;

    private Set<Comment> comments;

    public Homework() {
    }


    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public Homework setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    @Pattern(regexp = "http[s]*:\\/\\/github\\.com\\/.+")
    public String getGitAddress() {
        return gitAddress;
    }

    public Homework setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
        return this;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public Homework setAuthor(User author) {
        this.author = author;
        return this;
    }

    @ManyToOne
    public Exercise getExercise() {
        return exercise;
    }

    public Homework setExercise(Exercise exercise) {
        this.exercise = exercise;
        return this;
    }

    @OneToMany(mappedBy = "homework",fetch = FetchType.EAGER)
    public Set<Comment> getComments() {
        return comments;
    }

    public Homework setComments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }
}
