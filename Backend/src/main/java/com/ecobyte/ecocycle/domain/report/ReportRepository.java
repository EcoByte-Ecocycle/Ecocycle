package com.ecobyte.ecocycle.domain.report;

import java.util.List;
import org.springframework.data.repository.Repository;

public interface ReportRepository extends Repository<Report, Long> {

    Report save(final Report report);

    List<Report> findAll();
}
