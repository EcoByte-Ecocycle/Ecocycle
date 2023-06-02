package com.ecobyte.ecocycle.dto.response;

import lombok.Getter;

@Getter
public class DailyQuizResponse {

    private final Long id;
    private final QuizResponse quiz;

    public DailyQuizResponse(final Long id, final QuizResponse quiz) {
        this.id = id;
        this.quiz = quiz;
    }
}
