package com.ecobyte.ecocycle.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReportRequest {

    private static final String MISSING_REQUIRED_INPUT = "필수 입력 값이 누락됐습니다.";

    @NotBlank(message = MISSING_REQUIRED_INPUT)
    private String productName;
    private String imageUrl;

    public ReportRequest(final String productName, final String imageUrl) {
        this.productName = productName;
        this.imageUrl = imageUrl;
    }
}
