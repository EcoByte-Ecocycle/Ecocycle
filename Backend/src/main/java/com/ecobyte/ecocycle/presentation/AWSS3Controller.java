package com.ecobyte.ecocycle.presentation;

import com.ecobyte.ecocycle.application.AWSS3Service;
import com.ecobyte.ecocycle.dto.response.PresignedUrlResponse;
import com.ecobyte.ecocycle.presentation.auth.LoginAuthorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LoginAuthorization
public class AWSS3Controller {

    private final AWSS3Service awsS3Service;

    public AWSS3Controller(final AWSS3Service awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    @GetMapping("/api/presigned-url")
    public ResponseEntity<PresignedUrlResponse> requestPresignedUrl() {
        return ResponseEntity.ok(awsS3Service.getImagePresignedUrl());
    }
}
