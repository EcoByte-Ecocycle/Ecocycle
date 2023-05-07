package com.ecobyte.ecocycle.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @DisplayName("사용자가 관리자인지 확인한다")
    @Test
    void isAdmin() {
        // given & when
        final User user = new User("azpi", "azpi", "azpi@email.com", Role.ADMIN);

        // then
        assertThat(user.isAdmin()).isTrue();
    }

    @DisplayName("사용자가 관리자가 아닌 경우인지 확인한다")
    @Test
    void isNotAdmin() {
        // given & when
        final User user = new User("azpi", "azpi", "azpi@email.com", Role.USER);

        // then
        assertThat(user.isAdmin()).isFalse();
    }

}
