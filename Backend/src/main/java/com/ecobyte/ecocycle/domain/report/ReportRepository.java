package com.ecobyte.ecocycle.domain.report;

import org.springframework.data.repository.Repository;

public interface ReportRepository extends Repository<Report, Long> {

    Report save(final Report report);
}
