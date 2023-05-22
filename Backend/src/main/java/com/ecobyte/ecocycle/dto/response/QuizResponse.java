package com.ecobyte.ecocycle.dto.response;

import com.ecobyte.ecocycle.domain.quiz.Quiz;
import lombok.Getter;

@Getter
public class QuizResponse {

    private final Long id;
    private final String content;
    private final Boolean answer;
    private final String tip;

    public QuizResponse(final Long id, final String content, final Boolean answer, final String tip) {
        this.id = id;
        this.content = content;
        this.answer = answer;
        this.tip = tip;
    }

    public static QuizResponse from(final Quiz quiz) {
        return new QuizResponse(quiz.getId(), quiz.getContent(), quiz.getAnswer(), quiz.getTip());
    }

}
