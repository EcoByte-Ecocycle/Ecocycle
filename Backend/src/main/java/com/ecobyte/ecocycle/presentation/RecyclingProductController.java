package com.ecobyte.ecocycle.presentation;

import com.ecobyte.ecocycle.application.RecyclingProductService;
import com.ecobyte.ecocycle.application.auth.AdminAuthorization;
import com.ecobyte.ecocycle.dto.request.RecyclingProductRequest;
import com.ecobyte.ecocycle.dto.response.RecyclingProductResponse;
import com.ecobyte.ecocycle.presentation.auth.Authorization;
import com.ecobyte.ecocycle.presentation.auth.AuthorizationPrincipal;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class RecyclingProductController {

    private final RecyclingProductService recyclingProductService;

    public RecyclingProductController(final RecyclingProductService recyclingProductService) {
        this.recyclingProductService = recyclingProductService;
    }

    @PostMapping
    @Authorization
    @AdminAuthorization
    public ResponseEntity<RecyclingProductResponse> add(@AuthorizationPrincipal final Long loginId,
                                                        @RequestBody @Valid final RecyclingProductRequest request) {
        return ResponseEntity.ok(recyclingProductService.save(request));
    }
}
