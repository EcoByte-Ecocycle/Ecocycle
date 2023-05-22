package com.ecobyte.ecocycle.application;

import com.ecobyte.ecocycle.domain.quiz.Quiz;
import com.ecobyte.ecocycle.domain.quiz.QuizRecord;
import com.ecobyte.ecocycle.domain.quiz.QuizRecordRepository;
import com.ecobyte.ecocycle.domain.quiz.QuizRepository;
import com.ecobyte.ecocycle.domain.user.User;
import com.ecobyte.ecocycle.domain.user.UserRepository;
import com.ecobyte.ecocycle.dto.request.QuizRequest;
import com.ecobyte.ecocycle.dto.response.QuizResponse;
import com.ecobyte.ecocycle.exception.AlreadyExistedDailyQuizException;
import com.ecobyte.ecocycle.exception.NoQuizException;
import com.ecobyte.ecocycle.exception.UserNotFoundException;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizRecordRepository quizRecordRepository;

    public QuizService(final QuizRepository quizRepository,
                       final UserRepository userRepository,
                       final QuizRecordRepository quizRecordRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.quizRecordRepository = quizRecordRepository;
    }

    @Transactional
    public QuizResponse add(final QuizRequest quizRequest) {
        final Quiz quiz = new Quiz(quizRequest.getContent(), quizRequest.getAnswer(), quizRequest.getTip());
        final Quiz savedQuiz = quizRepository.save(quiz);
        return QuizResponse.from(savedQuiz);
    }

    public QuizResponse giveDailyQuiz(final Long loginId) {
        final LocalDate currentDate = LocalDate.now();
        checkDailyQuizAlreadyDid(loginId, currentDate);

        return QuizResponse.from(makeDailyQuiz(loginId, currentDate).getQuiz());

    }

    private void checkDailyQuizAlreadyDid(final Long loginId, final LocalDate currentDate) {
        final boolean isAlreadyExistedDailyQuiz = quizRecordRepository
                .existsByUserIdAndAttendanceDate(loginId, currentDate);

        if (isAlreadyExistedDailyQuiz) {
            throw new AlreadyExistedDailyQuizException();
        }
    }

    private QuizRecord makeDailyQuiz(final Long userId, final LocalDate attendanceDate) {
        final Quiz quiz = quizRepository.findByRandomOne()
                .orElseThrow(NoQuizException::new);
        final User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        final QuizRecord quizRecord = new QuizRecord(user, quiz, attendanceDate);
        return quizRecordRepository.save(quizRecord);
    }
}
