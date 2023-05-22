package com.ecobyte.ecocycle.acceptance;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import com.ecobyte.ecocycle.dto.request.DailyQuizAnswerRequest;
import com.ecobyte.ecocycle.dto.request.QuizRequest;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class QuizAcceptanceTest extends AcceptanceTest {

    @DisplayName("관리자가 퀴즈를 추가하면 200 OK를 응답한다.")
    @Test
    void add() {
        // given
        final String adminToken = loginAdmin();
        final QuizRequest quizRequest = new QuizRequest("content", true, "tip");

        // when
        final ValidatableResponse response = post("/api/quizzes", quizRequest, adminToken);

        // then
        response.statusCode(OK.value())
                .body("id", notNullValue())
                .body("content", equalTo(quizRequest.getContent()))
                .body("answer", equalTo(quizRequest.getAnswer()))
                .body("tip", equalTo(quizRequest.getTip()));
    }

    @DisplayName("데일리 퀴즈를 요청하면 200 OK를 응답한다.")
    @Test
    void giveDailyQuiz() {
        // given
        final String adminToken = loginAdmin();
        final QuizRequest quizRequest = new QuizRequest("content", true, "tip");
        post("/api/quizzes", quizRequest, adminToken);

        final String accessToken = loginUser();

        // when
        final ValidatableResponse response = post("/api/quizzes/today", accessToken);

        // then
        response.statusCode(OK.value())
                .body("id", notNullValue())
                .body("quiz.id", notNullValue())
                .body("quiz.content", notNullValue())
                .body("quiz.answer", notNullValue())
                .body("quiz.tip", notNullValue());
    }

    @DisplayName("데일리 퀴즈의 정답 여부를 업데이트하고 200 OK를 응답한다.")
    @Test
    void updateDailyAnswer() {
        // given
        final String adminToken = loginAdmin();
        final QuizRequest quizRequest = new QuizRequest("content", true, "tip");
        post("/api/quizzes", quizRequest, adminToken);

        final String accessToken = loginUser();
        final long dailyQuizId = post("/api/quizzes/today", accessToken).extract()
                .jsonPath()
                .getLong("id");
        final DailyQuizAnswerRequest dailyQuizAnswerRequest = new DailyQuizAnswerRequest(true);

        // when
        final ValidatableResponse response = RestAssured.given().log().all()
                .auth().oauth2(accessToken)
                .body(dailyQuizAnswerRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/api/quizzes/today/" + dailyQuizId)
                .then().log().all();

        // then
        response.statusCode(NO_CONTENT.value());
    }
}
