package com.example.judgev2_alone.model.bindingModel;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ExerciseBindingModel {

    private String name;

    private LocalDateTime startedOn;

    private LocalDateTime dueDate;

    public ExerciseBindingModel() {
    }

    @Size(min = 2)
    public String getName() {
        return name;
    }

    public ExerciseBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "The date cannot be in the future")
    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public ExerciseBindingModel setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public ExerciseBindingModel setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
