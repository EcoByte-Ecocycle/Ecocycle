package com.ecobyte.ecocycle.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DailyQuizAnswerRequest {

    private Boolean isRight;

    public DailyQuizAnswerRequest(final Boolean isRight) {
        this.isRight = isRight;
    }
}
