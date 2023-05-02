package com.ecobyte.ecocycle.presentation;

import com.ecobyte.ecocycle.application.UserService;
import com.ecobyte.ecocycle.dto.response.MainPageResponse;
import com.ecobyte.ecocycle.presentation.auth.Authorization;
import com.ecobyte.ecocycle.presentation.auth.AuthorizationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Authorization
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<MainPageResponse> findMyMainPage(@AuthorizationPrincipal final Long loginId) {
        MainPageResponse mainPageResponse = userService.findMyMainPage(loginId);
        return ResponseEntity.ok(mainPageResponse);
    }
}
