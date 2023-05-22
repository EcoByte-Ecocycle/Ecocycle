package com.ecobyte.ecocycle.application;

import com.ecobyte.ecocycle.domain.quiz.Quiz;
import com.ecobyte.ecocycle.domain.quiz.QuizRepository;
import com.ecobyte.ecocycle.dto.request.QuizRequest;
import com.ecobyte.ecocycle.dto.response.QuizResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(final QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Transactional
    public QuizResponse add(final QuizRequest quizRequest) {
        final Quiz quiz = new Quiz(quizRequest.getContent(), quizRequest.getAnswer(), quizRequest.getTip());
        final Quiz savedQuiz = quizRepository.save(quiz);
        return QuizResponse.from(savedQuiz);
    }
}
