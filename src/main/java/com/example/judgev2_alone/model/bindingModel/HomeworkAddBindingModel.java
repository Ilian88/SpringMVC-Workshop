package com.example.judgev2_alone.model.bindingModel;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class HomeworkAddBindingModel {

    private String exercise;

    private String gitAddress;


    public HomeworkAddBindingModel() {
    }

    @Size(min = 2)
    public String getExercise() {
        return exercise;
    }

    public HomeworkAddBindingModel setExercise(String exercise) {
        this.exercise = exercise;
        return this;
    }

    @Pattern(regexp = "http[s]*:\\/\\/github\\.com\\/.+")
    public String getGitAddress() {
        return gitAddress;
    }

    public HomeworkAddBindingModel setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
        return this;
    }
}
