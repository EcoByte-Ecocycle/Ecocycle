package com.ecobyte.ecocycle.presentation;

import com.ecobyte.ecocycle.application.ReportService;
import com.ecobyte.ecocycle.application.auth.AdminAuthorization;
import com.ecobyte.ecocycle.dto.request.ReportRequest;
import com.ecobyte.ecocycle.dto.response.ReportsResponse;
import com.ecobyte.ecocycle.dto.response.SavedReportResponse;
import com.ecobyte.ecocycle.presentation.auth.AuthorizationPrincipal;
import com.ecobyte.ecocycle.presentation.auth.LoginAuthorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<SavedReportResponse> add(@AuthorizationPrincipal final Long loginId,
                                                   @RequestBody final ReportRequest request) {
        return ResponseEntity.ok(reportService.add(loginId, request));
    }

    @GetMapping
    @AdminAuthorization
    public ResponseEntity<ReportsResponse> inquire(@AuthorizationPrincipal final Long loginId) {
        return ResponseEntity.ok(reportService.findALl());
    }
}
