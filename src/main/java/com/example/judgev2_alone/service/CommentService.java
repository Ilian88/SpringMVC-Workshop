package com.example.judgev2_alone.service;


import com.example.judgev2_alone.model.service.CommentServiceModel;

import java.util.Map;

public interface CommentService {
    void addComment(CommentServiceModel commentServiceModel, Long homeworkId);

    Double findAvgScore();

    Map<Integer,Integer> findScoreMap();
}
