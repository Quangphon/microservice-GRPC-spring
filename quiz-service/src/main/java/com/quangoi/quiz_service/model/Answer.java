package com.quangoi.quiz_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Answer {
    private Integer questionId  ;
    private Integer selectedOption;

}
