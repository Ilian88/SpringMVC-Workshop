package com.example.judgev2_alone.service.impl;

import com.example.judgev2_alone.model.entity.Exercise;
import com.example.judgev2_alone.model.service.ExerciseServiceModel;
import com.example.judgev2_alone.repository.ExerciseRepository;
import com.example.judgev2_alone.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addExercise(ExerciseServiceModel exerciseServiceModel) {
        this.exerciseRepository.save(modelMapper.map(exerciseServiceModel, Exercise.class));
    }

    @Override
    public List<String> findAllNames() {
        return this.exerciseRepository.findAllNames();
    }

    @Override
    public boolean checkIfisLate(String exercise) {
        Exercise exerciseEntity = this.exerciseRepository.findByName(exercise).orElse(null);

       return exerciseEntity.getDueDate().isBefore(LocalDateTime.now());

    }

    @Override
    public Exercise findExercise(String exercise) {
        return this.exerciseRepository.findByName(exercise).orElse(null);
    }
}
