package com.ecobyte.ecocycle.dto.response;

import lombok.Getter;

@Getter
public class SavedReportResponse {

    private final Long id;

    public SavedReportResponse(final Long id) {
        this.id = id;
    }
}
