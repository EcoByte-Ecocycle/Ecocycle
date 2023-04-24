package com.ecobyte.ecocycle.acceptance;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.OK;

import com.ecobyte.ecocycle.dto.response.GoogleProfileResponse;
import com.ecobyte.ecocycle.support.GoogleClient;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAcceptanceTest {

    @LocalServerPort
    int port;

    @MockBean
    private GoogleClient googleClient;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("로그인된 사용자가 정보 요청 시 200 OK를 응답한다.")
    @Test
    void findMyInfo() {
        // given
        given(googleClient.getIdToken(anyString()))
                .willReturn("something");
        given(googleClient.getProfileResponse(anyString()))
                .willReturn(new GoogleProfileResponse("azpi@gmail.com", "azpi"));
        final String accessToken = RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/login?code=anyCode")
                .then().log().all()
                .extract().jsonPath().get("accessToken");

        // when
        final ValidatableResponse userResponse = RestAssured.given().log().all()
                .auth().oauth2(accessToken)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/users/me")
                .then().log().all();

        // then
        userResponse.statusCode(OK.value())
                .body("nickname", equalTo("azpi"))
                .body("stamps", equalTo(0))
                .body("dailyQuiz", equalTo(false));
    }
}
