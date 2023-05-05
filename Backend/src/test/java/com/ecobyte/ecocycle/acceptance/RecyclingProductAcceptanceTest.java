package com.ecobyte.ecocycle.acceptance;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.OK;

import com.ecobyte.ecocycle.dto.request.RecyclingProductRequest;
import com.ecobyte.ecocycle.dto.response.GoogleProfileResponse;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecyclingProductAcceptanceTest extends AcceptanceTest {

    @DisplayName("관리자 제품 추가 기능이 성공하면 200 OK를 응답한다.")
    @Test
    void add() {
        // given
        given(googleClient.getIdToken(anyString()))
                .willReturn("something");
        given(googleClient.getProfileResponse(anyString()))
                .willReturn(new GoogleProfileResponse(adminEmail, "admin"));

        final String accessToken = getAccessToken();
        final RecyclingProductRequest recyclingProductRequest = new RecyclingProductRequest("바나나껍질",
                "종량제에 버려주세요.", "바나나 껍질은 일반쓰레기입니다.");

        // when
        final ValidatableResponse productResponse = post("/products", recyclingProductRequest, accessToken);

        // then
        productResponse.statusCode(OK.value())
                .body("id", notNullValue())
                .body("name", equalTo(recyclingProductRequest.getName()))
                .body("recyclingInfo", equalTo(recyclingProductRequest.getRecyclingInfo()))
                .body("tip", equalTo(recyclingProductRequest.getTip()));
    }
}
