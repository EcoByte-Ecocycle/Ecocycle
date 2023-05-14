package com.ecobyte.ecocycle.acceptance;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

import com.ecobyte.ecocycle.dto.request.RecyclingProductRequest;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecyclingProductAcceptanceTest extends AcceptanceTest {

    @DisplayName("관리자 제품 추가 기능이 성공하면 200 OK를 응답한다.")
    @Test
    void add() {
        // given
        final String accessToken = loginAdmin();
        final RecyclingProductRequest recyclingProductRequest = new RecyclingProductRequest("바나나껍질",
                "종량제에 버려주세요.", "바나나 껍질은 일반쓰레기입니다.");

        // when
        final ValidatableResponse productResponse = post("/api/products", recyclingProductRequest, accessToken);

        // then
        productResponse.statusCode(OK.value())
                .body("id", notNullValue())
                .body("name", equalTo(recyclingProductRequest.getName()))
                .body("recyclingInfo", equalTo(recyclingProductRequest.getRecyclingInfo()))
                .body("tip", equalTo(recyclingProductRequest.getTip()));
    }

    @DisplayName("관리자 제품 추가 기능 입력값이 누락되면 400을 응답한다.")
    @Test
    void add_error_missingInputValue() {
        // given
        final String accessToken = loginAdmin();
        final RecyclingProductRequest recyclingProductRequest = new RecyclingProductRequest("바나나껍질",
                "종량제에 버려주세요.", null);

        // when
        final ValidatableResponse errorResponse = post("/api/products", recyclingProductRequest, accessToken);

        // then
        errorResponse.statusCode(BAD_REQUEST.value());
    }

    @DisplayName("관리자 제품 추가 기능을 관리자가 아닌 사용자가 요청하면 403을 응답한다.")
    @Test
    void add_error_not_admin() {
        // given
        final String accessToken = loginUser();
        final RecyclingProductRequest recyclingProductRequest = new RecyclingProductRequest("바나나껍질",
                "종량제에 버려주세요.", "바나나 껍질은 일반쓰레기입니다.");

        // when
        final ValidatableResponse errorResponse = post("/api/products", recyclingProductRequest, accessToken);

        // then
        errorResponse.statusCode(FORBIDDEN.value());
    }
}
