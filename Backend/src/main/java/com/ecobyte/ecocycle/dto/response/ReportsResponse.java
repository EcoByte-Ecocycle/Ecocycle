package com.ecobyte.ecocycle.dto.response;

import com.ecobyte.ecocycle.domain.report.Report;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ReportsResponse {

    private final List<ReportResponse> reports;

    public ReportsResponse(final List<ReportResponse> reports) {
        this.reports = reports;
    }

    public static ReportsResponse from(final List<Report> reports) {
        return new ReportsResponse(reports.stream()
                .map(ReportResponse::from)
                .collect(Collectors.toList()));
    }
}
