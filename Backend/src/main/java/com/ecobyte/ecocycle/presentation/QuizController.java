package com.ecobyte.ecocycle.presentation;

import com.ecobyte.ecocycle.application.QuizService;
import com.ecobyte.ecocycle.dto.request.QuizRequest;
import com.ecobyte.ecocycle.dto.response.QuizResponse;
import com.ecobyte.ecocycle.presentation.auth.LoginAuthorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LoginAuthorization
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(final QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<QuizResponse> add(@RequestBody final QuizRequest quizRequest) {
        return ResponseEntity.ok(quizService.add(quizRequest));
    }
}
