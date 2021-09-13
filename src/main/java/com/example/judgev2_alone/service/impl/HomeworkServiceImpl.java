package com.example.judgev2_alone.service.impl;

import com.example.judgev2_alone.model.entity.Exercise;
import com.example.judgev2_alone.model.entity.Homework;
import com.example.judgev2_alone.model.entity.User;
import com.example.judgev2_alone.model.service.HomeworkServiceModel;
import com.example.judgev2_alone.repository.HomeworkRepository;
import com.example.judgev2_alone.security.CurrentUser;
import com.example.judgev2_alone.service.ExerciseService;
import com.example.judgev2_alone.service.HomeworkService;
import com.example.judgev2_alone.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ExerciseService exerciseService,
                               CurrentUser currentUser, UserService userService, ModelMapper modelMapper) {
        this.homeworkRepository = homeworkRepository;
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addHomework(String exercise, String gitAddress) {
        Homework homework = new Homework();
        Exercise exerciseEntity = this.exerciseService.findExercise(exercise);
        User author = this.userService.findUserByUsername(this.currentUser.getUsername());

        homework.setGitAddress(gitAddress);
        homework.setAuthor(author);

        if (exerciseEntity != null) {
            homework.setExercise(exerciseEntity);
        }

        homework.setAddedOn(LocalDateTime.now());

        this.homeworkRepository.save(homework);

    }

    @Override
    public HomeworkServiceModel findHomework() {
        Homework homework = this.homeworkRepository.findTop1ByOrderByComments().orElse(null);
        return modelMapper.map(homework,HomeworkServiceModel.class);
    }

    @Override
    public Homework findHomeworkById(Long homeworkId) {
        return this.homeworkRepository.findById(homeworkId).orElse(null);
    }
}
