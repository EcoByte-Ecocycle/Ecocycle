package com.ecobyte.ecocycle.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.ecobyte.ecocycle.domain.quiz.DailyQuiz;
import com.ecobyte.ecocycle.domain.quiz.DailyQuizRepository;
import com.ecobyte.ecocycle.domain.quiz.Quiz;
import com.ecobyte.ecocycle.domain.quiz.QuizRepository;
import com.ecobyte.ecocycle.domain.user.Role;
import com.ecobyte.ecocycle.domain.user.User;
import com.ecobyte.ecocycle.domain.user.UserRepository;
import com.ecobyte.ecocycle.dto.request.DailyQuizAnswerRequest;
import com.ecobyte.ecocycle.dto.response.DailyQuizResponse;
import com.ecobyte.ecocycle.exception.AlreadyExistedDailyQuizException;
import com.ecobyte.ecocycle.exception.DailyQuizOwnedException;
import com.ecobyte.ecocycle.support.DatabaseCleanUp;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuizServiceTest {

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private DailyQuizRepository dailyQuizRepository;

    @Autowired
    private DatabaseCleanUp databaseCleanUp;

    @BeforeEach
    void setUp() {
        databaseCleanUp.execute();
    }

    @DisplayName("데일리 퀴즈를 낸다.")
    @Test
    void giveDailyQuiz() {
        // given
        final User user = userRepository.save(new User("azpi", "azpi", "azpi@email.com", Role.USER));
        quizRepository.save(new Quiz("안경테는 플라스틱일까요?", false, "일반쓰레기입니다!"));
        final Quiz lastSavedQuiz = quizRepository.save(new Quiz("안경테는 플라스틱일까요?", false, "일반쓰레기입니다!"));

        // when
        final DailyQuizResponse dailyQuizResponse = quizService.giveDailyQuiz(user.getId());

        // then
        assertThat(dailyQuizResponse.getId())
                .isNotNull()
                .isLessThanOrEqualTo(lastSavedQuiz.getId())
                .isPositive();
    }

    @DisplayName("이미 데일리 퀴즈를 푼 경우 예외를 발생한다.")
    @Test
    void giveDailyQuiz_alreadyDid() {
        // given
        final LocalDate currentDate = LocalDate.now();
        final User user = userRepository.save(new User("azpi", "azpi", "azpi@email.com", Role.USER));
        final Quiz quiz = quizRepository.save(new Quiz("안경테는 플라스틱일까요?", false, "일반쓰레기입니다!"));
        dailyQuizRepository.save(new DailyQuiz(user, quiz, currentDate));

        // when & then
        assertThatThrownBy(() -> quizService.giveDailyQuiz(user.getId()))
                .isInstanceOf(AlreadyExistedDailyQuizException.class);
    }

    @DisplayName("데일리 퀴즈 정답 여부를 기록한다.")
    @Test
    void updateDailyAnswer() {
        // given
        final LocalDate currentDate = LocalDate.now();
        final User user = userRepository.save(new User("azpi", "azpi", "azpi@email.com", Role.USER));
        final Quiz quiz = quizRepository.save(new Quiz("안경테는 플라스틱일까요?", false, "일반쓰레기입니다!"));
        final DailyQuiz dailyQuiz = dailyQuizRepository.save(new DailyQuiz(user, quiz, currentDate));
        final DailyQuizAnswerRequest dailyQuizAnswerRequest = new DailyQuizAnswerRequest(true);

        // when & then
        assertThatCode(() -> quizService.updateDailyAnswer(user.getId(), dailyQuiz.getId(), dailyQuizAnswerRequest))
                .doesNotThrowAnyException();
    }

    @DisplayName("데일리 퀴즈 정답 여부를 기록한다.")
    @Test
    void updateDailyAnswer_notOwnedUser() {
        // given
        final LocalDate currentDate = LocalDate.now();
        final User user = userRepository.save(new User("azpi", "azpi", "azpi@email.com", Role.USER));
        final Quiz quiz = quizRepository.save(new Quiz("안경테는 플라스틱일까요?", false, "일반쓰레기입니다!"));
        final DailyQuiz dailyQuiz = dailyQuizRepository.save(new DailyQuiz(user, quiz, currentDate));
        final DailyQuizAnswerRequest dailyQuizAnswerRequest = new DailyQuizAnswerRequest(true);

        // when & then
        assertThatThrownBy(
                () -> quizService.updateDailyAnswer(user.getId() + 1, dailyQuiz.getId(), dailyQuizAnswerRequest))
                .isInstanceOf(DailyQuizOwnedException.class);
    }
}
