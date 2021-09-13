package com.example.judgev2_alone.service;


import com.example.judgev2_alone.model.entity.Homework;
import com.example.judgev2_alone.model.service.HomeworkServiceModel;

public interface HomeworkService {
    void addHomework(String exercise, String gitAddress);

    HomeworkServiceModel findHomework();

    Homework findHomeworkById(Long homeworkId);
}
