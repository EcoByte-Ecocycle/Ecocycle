package com.ecobyte.ecocycle.application;

import com.ecobyte.ecocycle.domain.user.User;
import com.ecobyte.ecocycle.domain.user.UserRepository;
import com.ecobyte.ecocycle.dto.response.MainPageResponse;
import com.ecobyte.ecocycle.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MainPageResponse findMyMainPage(final Long loginId) {
        final User loginUser = userRepository.findById(loginId)
                .orElseThrow(UserNotFoundException::new);

        return new MainPageResponse(loginUser.getNickname(), loginUser.getStamps(), false);
    }
}
