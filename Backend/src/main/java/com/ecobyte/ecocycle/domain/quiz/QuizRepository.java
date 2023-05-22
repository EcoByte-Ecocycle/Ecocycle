package com.ecobyte.ecocycle.domain.quiz;

import org.springframework.data.repository.Repository;

public interface QuizRepository extends Repository<Quiz, Long> {

    Quiz save(final Quiz quiz);
}
