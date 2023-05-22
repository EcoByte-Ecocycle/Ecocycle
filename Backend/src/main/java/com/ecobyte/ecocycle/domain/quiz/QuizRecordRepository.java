package com.ecobyte.ecocycle.domain.quiz;

import java.time.LocalDate;
import org.springframework.data.repository.Repository;

public interface QuizRecordRepository extends Repository<QuizRecord, Long> {

    QuizRecord save(final QuizRecord quizRecord);

    boolean existsByUserIdAndAttendanceDate(final Long userId, final LocalDate attendanceDate);
}
