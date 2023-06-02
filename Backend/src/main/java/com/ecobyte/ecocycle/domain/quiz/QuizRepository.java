package com.ecobyte.ecocycle.domain.quiz;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface QuizRepository extends Repository<Quiz, Long> {

    Quiz save(final Quiz quiz);

    @Query(value = "SELECT * FROM quiz ORDER BY RAND() limit 1", nativeQuery = true)
    Optional<Quiz> findByRandomOne();
}
