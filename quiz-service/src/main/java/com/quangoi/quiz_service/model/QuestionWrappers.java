package com.quangoi.quiz_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionWrappers {
    private Integer id;
    private String question;
    private List<String> options;
    private Integer correctOption;


    public QuestionWrappers(Integer id, String question, List<String> options, Integer correctOption) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}
