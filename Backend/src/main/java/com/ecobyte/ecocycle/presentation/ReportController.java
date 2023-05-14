package com.ecobyte.ecocycle.presentation;

import com.ecobyte.ecocycle.application.ReportService;
import com.ecobyte.ecocycle.dto.request.ReportRequest;
import com.ecobyte.ecocycle.dto.response.ReportResponse;
import com.ecobyte.ecocycle.presentation.auth.AuthorizationPrincipal;
import com.ecobyte.ecocycle.presentation.auth.LoginAuthorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@LoginAuthorization
public class ReportController {

    private final ReportService reportService;

    public ReportController(final ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ReportResponse> add(@AuthorizationPrincipal final Long loginId,
                                              @RequestBody final ReportRequest request) {
        return ResponseEntity.ok(reportService.add(loginId, request));
    }
}
