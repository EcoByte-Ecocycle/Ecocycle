package com.ecobyte.ecocycle.dto.response;

import lombok.Getter;

@Getter
public class ReportResponse {

    private final Long id;

    public ReportResponse(final Long id) {
        this.id = id;
    }
}
