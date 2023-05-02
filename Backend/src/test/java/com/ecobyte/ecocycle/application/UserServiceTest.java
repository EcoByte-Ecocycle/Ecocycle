package com.ecobyte.ecocycle.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.ecobyte.ecocycle.domain.user.Role;
import com.ecobyte.ecocycle.domain.user.User;
import com.ecobyte.ecocycle.domain.user.UserRepository;
import com.ecobyte.ecocycle.dto.response.MainPageResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("로그인 된 유저의 메인페이지에 필요한 정보를 응답한다.")
    @Test
    void findMyMainPage() {
        // given
        final User user = new User("azpi", "azpi", "azpi@gmail.com", Role.USER);
        final User savedUser = userRepository.save(user);
        final MainPageResponse expectedMainPageResponse1 = new MainPageResponse("azpi", 0, false);

        // when
        final MainPageResponse mainPageResponse = userService.findMyMainPage(savedUser.getId());

        // then
        assertThat(mainPageResponse).usingRecursiveComparison()
                .isEqualTo(expectedMainPageResponse1);
    }
}
