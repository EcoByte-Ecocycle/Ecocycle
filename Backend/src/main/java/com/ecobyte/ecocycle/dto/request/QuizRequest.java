package com.ecobyte.ecocycle.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class QuizRequest {

    private String content;
    private Boolean answer;
    private String tip;

    public QuizRequest(final String content, final Boolean answer, final String tip) {
        this.content = content;
        this.answer = answer;
        this.tip = tip;
    }
}
