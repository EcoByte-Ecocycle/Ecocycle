package com.ecobyte.ecocycle.acceptance;

import static org.hamcrest.Matchers.notNullValue;
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
public class LoginAcceptanceTest {

    @LocalServerPort
    int port;

    @MockBean
    private GoogleClient googleClient;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("구글 로그인에 성공하면 200 OK를 응답한다.")
    @Test
    void loginWithGoogle() {
        // given
        given(googleClient.getIdToken(anyString()))
                .willReturn("something");
        given(googleClient.getProfileResponse(anyString()))
                .willReturn(new GoogleProfileResponse("azpi@gmail.com", "azpi"));
        
        // when
        final ValidatableResponse response = RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/login?code=anyCode")
                .then().log().all();

        // then
        response.statusCode(OK.value())
                .body("accessToken", notNullValue());
    }
}
