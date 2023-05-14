package com.ecobyte.ecocycle.application;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.ecobyte.ecocycle.dto.response.PresignedUrlResponse;
import java.net.URL;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AWSS3Service {

    private static final String UPLOAD_FOLDER_PATH = "upload/report-image";
    
    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public AWSS3Service(final AmazonS3 amazonS3) {
        this.s3Client = amazonS3;
    }

    public PresignedUrlResponse getImagePresignedUrl() {
        return new PresignedUrlResponse(getPresignedUrl());
    }

    private String getPresignedUrl() {
        String fileName = UUID.randomUUID().toString();

        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60; // 1시간
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,
                UPLOAD_FOLDER_PATH + fileName)
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration);

        generatePresignedUrlRequest.addRequestParameter(Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString());

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toExternalForm();
    }
}
