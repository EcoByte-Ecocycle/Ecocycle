package com.ecobyte.ecocycle.application.auth;

import com.ecobyte.ecocycle.domain.user.Role;
import com.ecobyte.ecocycle.domain.user.User;
import com.ecobyte.ecocycle.domain.user.UserRepository;
import com.ecobyte.ecocycle.dto.response.GoogleProfileResponse;
import com.ecobyte.ecocycle.dto.response.LoginResponse;
import com.ecobyte.ecocycle.exception.UserNotFoundException;
import com.ecobyte.ecocycle.support.GoogleClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthService {

    private final GoogleClient googleClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public AuthService(final GoogleClient googleClient, final JwtTokenProvider jwtTokenProvider,
                       final UserRepository userRepository) {
        this.googleClient = googleClient;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Transactional
    public LoginResponse loginWithGoogle(final String code) {
        final String googleIdToken = googleClient.getIdToken(code);
        final GoogleProfileResponse profileResponse = googleClient.getProfileResponse(googleIdToken);
        final User user = userRepository.findByEmail(profileResponse.getEmail())
                .orElseGet(() -> saveGoogleUser(profileResponse));

        return createLoginResponse(user.getId());
    }

    public boolean isAdmin(final Long loginId) {
        final User expectedAdmin = userRepository.findById(loginId).orElseThrow(UserNotFoundException::new);
        return expectedAdmin.isAdmin();
    }

    private User saveGoogleUser(final GoogleProfileResponse profileResponse) {
        final User userToSave = new User(profileResponse.getName(), profileResponse.getName(),
                profileResponse.getEmail(), Role.USER);
        return userRepository.save(userToSave);
    }

    private LoginResponse createLoginResponse(final Long userId) {
        final String accessToken = jwtTokenProvider.create(String.valueOf(userId));
        return new LoginResponse(accessToken);
    }
}
