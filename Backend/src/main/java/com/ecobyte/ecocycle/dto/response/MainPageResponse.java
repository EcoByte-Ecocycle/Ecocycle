package com.ecobyte.ecocycle.dto.response;


import lombok.Getter;

@Getter
public class MainPageResponse {

    private final String nickname;
    private final Integer stamps;
    private final Boolean dailyQuiz;

    public MainPageResponse(final String nickname, final Integer stamps, final Boolean dailyQuiz) {
        this.nickname = nickname;
        this.stamps = stamps;
        this.dailyQuiz = dailyQuiz;
    }
}
