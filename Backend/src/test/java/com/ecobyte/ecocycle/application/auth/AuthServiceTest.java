package com.ecobyte.ecocycle.application.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.ecobyte.ecocycle.domain.user.Role;
import com.ecobyte.ecocycle.domain.user.User;
import com.ecobyte.ecocycle.domain.user.UserRepository;
import com.ecobyte.ecocycle.dto.response.GoogleProfileResponse;
import com.ecobyte.ecocycle.dto.response.LoginResponse;
import com.ecobyte.ecocycle.support.GoogleClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private GoogleClient googleClient;

    @Autowired
    private UserRepository userRepository;

    @Value("${admin.email}")
    private String adminEmail;

    @DisplayName("구글 OAuth 로그인 시 회원가입이 이루어졌는지 확인한다.")
    @Test
    void loginWithGoogle() {
        // given
        given(googleClient.getIdToken(anyString()))
                .willReturn("something");
        given(googleClient.getProfileResponse(anyString()))
                .willReturn(new GoogleProfileResponse("azpi@gmail.com", "azpi"));
        final User expectedUser = new User(null, "azpi", "azpi", "azpi@gmail.com", Role.USER);

        // when
        final LoginResponse loginResponse = authService.loginWithGoogle("codecode");
        final String payload = jwtTokenProvider.getPayload(loginResponse.getAccessToken());
        final User user = userRepository.findById(Long.parseLong(payload)).get();

        // then
        assertThat(user).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedUser);
    }

    @DisplayName("마스터 인가를 확인한다.")
    @Test
    void isAdmin() {
        // given
        System.out.println(adminEmail);
        final User adminUser = userRepository.findByEmail(adminEmail).get();
        final Long adminUserId = adminUser.getId();

        // when & then
        assertThat(authService.isAdmin(adminUserId)).isTrue();
    }
}
