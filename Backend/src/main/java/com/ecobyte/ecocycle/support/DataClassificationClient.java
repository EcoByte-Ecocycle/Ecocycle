package com.ecobyte.ecocycle.support;

import com.ecobyte.ecocycle.dto.response.DataClassificationResponse;
import com.ecobyte.ecocycle.exception.InvalidImageUrlException;
import com.ecobyte.ecocycle.exception.NoDataException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class DataClassificationClient {

    private final RestTemplate restTemplate;

    private final String url;

    public DataClassificationClient(final RestTemplate restTemplate,
                                    @Value("${data.classification.url}") final String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public String classifyProduct(final String imageUrl) {
        final HttpHeaders headers = getUrlEncodedHeader();
        final HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(null, headers);
        return getClassifiedProductName(httpEntity, imageUrl);
    }

    private HttpHeaders getUrlEncodedHeader() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private String getClassifiedProductName(final HttpEntity<MultiValueMap<String, String>> httpEntity,
                                            final String imageUrl) {
        try {
            ResponseEntity<DataClassificationResponse> response = restTemplate
                    .exchange(url + "/api/datas?url=" + imageUrl, HttpMethod.GET, httpEntity,
                            DataClassificationResponse.class);

            if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
                throw new NoDataException();
            }

            return Objects.requireNonNull(response.getBody()).getProductName();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new InvalidImageUrlException();
            }

            throw e;
        }
    }
}
