package com.example.judgev2_alone.service;

import com.example.judgev2_alone.model.entity.Exercise;
import com.example.judgev2_alone.model.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    void addExercise(ExerciseServiceModel exerciseServiceModel);

    List<String> findAllNames();


    boolean checkIfisLate(String exercise);

    Exercise findExercise(String exercise);
}
