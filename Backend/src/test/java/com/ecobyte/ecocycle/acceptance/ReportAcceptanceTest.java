package com.ecobyte.ecocycle.acceptance;

import static org.hamcrest.Matchers.containsInAnyOrder;
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

    @DisplayName("관리자가 제보 확인 요청을 하면 200 OK를 응답한다.")
    @Test
    void get_reports() {
        // given
        final String accessToken = loginUser();
        final ReportRequest reportRequest = new ReportRequest("바나나껍질", "imageUrl");
        final ReportRequest reportRequest2 = new ReportRequest("칫솔", "imageUrl2");
        final Integer reportId1 = post("/api/reports", reportRequest, accessToken).extract().jsonPath().get("id");
        final Integer reportId2 = post("/api/reports", reportRequest2, accessToken).extract().jsonPath().get("id");

        final String adminToken = loginAdmin();

        // when
        final ValidatableResponse response = get("/api/reports", adminToken);

        // then
        response.statusCode(OK.value())
                .body("reports.id", containsInAnyOrder(reportId1, reportId2))
                .body("reports.productName", containsInAnyOrder("바나나껍질", "칫솔"))
                .body("reports.imageUrl", containsInAnyOrder("imageUrl", "imageUrl2"));
    }
}
