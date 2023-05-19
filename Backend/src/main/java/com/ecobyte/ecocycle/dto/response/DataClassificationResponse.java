package com.ecobyte.ecocycle.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DataClassificationResponse {

    @JsonProperty("name")
    private String productName;

    public DataClassificationResponse(final String productName) {
        this.productName = productName;
    }
}
