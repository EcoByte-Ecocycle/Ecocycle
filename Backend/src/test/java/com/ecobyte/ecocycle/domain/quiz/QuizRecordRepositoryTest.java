package com.ecobyte.ecocycle.domain.quiz;

import static org.assertj.core.api.Assertions.assertThat;

import com.ecobyte.ecocycle.domain.user.Role;
import com.ecobyte.ecocycle.domain.user.User;
import com.ecobyte.ecocycle.domain.user.UserRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class QuizRecordRepositoryTest {

    @Autowired
    private QuizRecordRepository quizRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @DisplayName("사용자 id와 날짜로 데일리 퀴즈를 이미 풀었는지 확인한다(풀었을 경우)")
    @Test
    void existsByUserIdAndAttendanceDate() {
        // given
        final LocalDate currentDate = LocalDate.now();
        final User user = userRepository.save(new User("azpi", "azpi", "azpi@email.com", Role.USER));
        final Quiz quiz = quizRepository.save(new Quiz("안경테는 플라스틱일까요?", false, "일반쓰레기입니다!"));
        quizRecordRepository.save(new QuizRecord(user, quiz, currentDate));

        // when
        final boolean alreadyDid = quizRecordRepository
                .existsByUserIdAndAttendanceDate(user.getId(), currentDate);

        // then
        assertThat(alreadyDid).isTrue();
    }

    @DisplayName("사용자 id와 날짜로 데일리 퀴즈를 이미 풀었는지 확인한다(풀지 않았을 경우)")
    @Test
    void existsByUserIdAndAttendanceDate_false() {
        // given
        final LocalDate currentDate = LocalDate.now();
        final User user = userRepository.save(new User("azpi", "azpi", "azpi@email.com", Role.USER));

        // when
        final boolean alreadyDid = quizRecordRepository
                .existsByUserIdAndAttendanceDate(user.getId(), currentDate);

        // then
        assertThat(alreadyDid).isFalse();
    }
}
