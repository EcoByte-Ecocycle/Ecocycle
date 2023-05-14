package com.ecobyte.ecocycle.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecyclingProductRequest {

    private static final String MISSING_REQUIRED_INPUT = "필수 입력 값이 누락됐습니다.";

    @NotBlank(message = MISSING_REQUIRED_INPUT)
    private String name;

    @NotBlank(message = MISSING_REQUIRED_INPUT)
    private String recyclingInfo;

    @NotBlank(message = MISSING_REQUIRED_INPUT)
    private String tip;

    public RecyclingProductRequest(final String name, final String recyclingInfo, final String tip) {
        this.name = name;
        this.recyclingInfo = recyclingInfo;
        this.tip = tip;
    }
}
