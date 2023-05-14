package com.ecobyte.ecocycle.acceptance;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.OK;

import com.ecobyte.ecocycle.dto.request.ReportRequest;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReportAcceptanceTest extends AcceptanceTest {

    @DisplayName("사용자에게 제보를 받고 200 OK를 응답한다.")
    @Test
    void add_report() {
        // given
        final String accessToken = loginUser();
        final ReportRequest reportRequest = new ReportRequest("바나나껍질", "imageUrl");

        // when
        final ValidatableResponse response = post("/api/reports", reportRequest, accessToken);

        // then
        response.statusCode(OK.value())
                .body("id", notNullValue());
    }
}
