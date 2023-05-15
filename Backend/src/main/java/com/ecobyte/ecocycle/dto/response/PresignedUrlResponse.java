package com.ecobyte.ecocycle.dto.response;

import lombok.Getter;

@Getter
public class PresignedUrlResponse {

    private final String presignedUrl;

    public PresignedUrlResponse(final String presignedUrl) {
        this.presignedUrl = presignedUrl;
    }
}
