package com.ecobyte.ecocycle.domain.quiz;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface DailyQuizRepository extends Repository<DailyQuiz, Long> {

    DailyQuiz save(final DailyQuiz dailyQuiz);

    boolean existsByUserIdAndAttendanceDate(final Long userId, final LocalDate attendanceDate);

    Optional<DailyQuiz> findById(final Long dailyQuizId);
}
