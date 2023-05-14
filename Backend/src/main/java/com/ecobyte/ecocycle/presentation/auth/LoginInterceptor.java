package com.ecobyte.ecocycle.presentation.auth;

import com.ecobyte.ecocycle.application.auth.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    public LoginInterceptor(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) {
        if (isNotOptionsMethod(request) && isAnnotated(handler)) {
            validateToken(request);
        }

        return true;
    }

    private boolean isNotOptionsMethod(final HttpServletRequest request) {
        return HttpMethod.valueOf(request.getMethod()) != HttpMethod.OPTIONS;
    }

    private boolean isAnnotated(final Object handler) {
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final LoginAuthorization classAnnotation = handlerMethod.getBeanType().getAnnotation(LoginAuthorization.class);
        final LoginAuthorization methodAnnotation = handlerMethod.getMethodAnnotation(LoginAuthorization.class);
        return Objects.nonNull(classAnnotation) || Objects.nonNull(methodAnnotation);
    }

    private void validateToken(final HttpServletRequest request) {
        final String token = AuthorizationExtractor.extract(request);
        jwtTokenProvider.validateToken(token);
    }
}
