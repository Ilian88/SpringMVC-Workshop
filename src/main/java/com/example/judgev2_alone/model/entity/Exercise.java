package com.example.judgev2_alone.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "exercise")
public class Exercise extends BaseEntity{

    private String name;

    private LocalDateTime startedOn;

    private LocalDateTime dueDate;

    public Exercise() {
    }

    @Column(name = "name",nullable = false,unique = true)
    @Size(min = 2)
    public String getName() {
        return name;
    }

    public Exercise setName(String name) {
        this.name = name;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "The date cannot be in the future")
    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public Exercise setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Exercise setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
