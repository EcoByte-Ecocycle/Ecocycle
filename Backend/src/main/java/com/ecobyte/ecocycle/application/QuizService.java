package com.ecobyte.ecocycle.application;

import com.ecobyte.ecocycle.dto.request.QuizRequest;
import com.ecobyte.ecocycle.dto.response.QuizResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QuizService {

    public QuizResponse add(final QuizRequest quizRequest) {
        return null;
    }
}
