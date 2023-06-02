package com.ecobyte.ecocycle.acceptance;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.ecobyte.ecocycle.domain.user.Role;
import com.ecobyte.ecocycle.domain.user.User;
import com.ecobyte.ecocycle.domain.user.UserRepository;
import com.ecobyte.ecocycle.dto.response.GoogleProfileResponse;
import com.ecobyte.ecocycle.support.DataClassificationClient;
import com.ecobyte.ecocycle.support.DatabaseCleanUp;
import com.ecobyte.ecocycle.support.GoogleClient;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @MockBean
    protected GoogleClient googleClient;
    @MockBean
    protected DataClassificationClient dataClassificationClient;
    @Value("${admin.email}")
    protected String adminEmail;
    @LocalServerPort
    int port;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DatabaseCleanUp databaseCleanUp;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        databaseCleanUp.execute();
        userRepository.save(new User("admin", "admin", adminEmail, Role.ADMIN));
    }

    protected String loginUser() {
        given(googleClient.getIdToken(anyString()))
                .willReturn("something");
        given(googleClient.getProfileResponse(anyString()))
                .willReturn(new GoogleProfileResponse("azpi@gmail.com", "azpi"));

        return getAccessToken();
    }

    protected String loginAdmin() {
        given(googleClient.getIdToken(anyString()))
                .willReturn("something");
        given(googleClient.getProfileResponse(anyString()))
                .willReturn(new GoogleProfileResponse(adminEmail, "admin"));

        return getAccessToken();
    }

    protected String getAccessToken() {
        return RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/login?code=anyCode")
                .then().log().all()
                .extract().jsonPath().get("accessToken");
    }

    protected ValidatableResponse post(final String uri, final Object requestBody, final String token) {
        return RestAssured.given().log().all()
                .auth().oauth2(token)
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post(uri)
                .then().log().all();
    }

    protected ValidatableResponse post(final String uri, final String token) {
        return RestAssured.given().log().all()
                .auth().oauth2(token)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post(uri)
                .then().log().all();
    }

    protected ValidatableResponse get(final String uri, final String token) {
        return RestAssured.given().log().all()
                .auth().oauth2(token)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get(uri)
                .then().log().all();
    }
}
