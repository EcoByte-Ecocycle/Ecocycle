package com.ecobyte.ecocycle.domain.quiz;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    @DisplayName("퀴즈를 제대로 가져오는지 확인한다")
    @Test
    void findByRandomOne() {
        // given
        final Quiz quiz1 = new Quiz("안경테는 플라스틱일까?", false, "일반쓰레기 입니다!");
        final Quiz quiz2 = new Quiz("안경테는 플라스틱일까?", false, "일반쓰레기 입니다!");
        quizRepository.save(quiz1);
        final Quiz savedQuiz2 = quizRepository.save(quiz2);

        // when
        final Quiz quiz = quizRepository.findByRandomOne().get();

        // then
        assertThat(quiz.getId()).isLessThanOrEqualTo(savedQuiz2.getId())
                .isPositive();
    }

    @DisplayName("퀴즈가 없는 경우에 예외가 발생하지 않는능 검사한다.")
    @Test
    void findByRandomOne_noQUiz() {
        // given & when
        final Optional<Quiz> quiz = quizRepository.findByRandomOne();

        // then
        assertThat(quiz.isEmpty()).isTrue();
    }
}
