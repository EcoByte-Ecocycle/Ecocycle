package com.ecobyte.ecocycle;

import com.ecobyte.ecocycle.domain.user.Role;
import com.ecobyte.ecocycle.domain.user.User;
import com.ecobyte.ecocycle.domain.user.UserRepository;
import java.util.Optional;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements ApplicationListener<ContextRefreshedEvent> {

    private static final String ADMIN_EMAIL = "inha.ecobyte@gmail.com";
    
    private final UserRepository userRepository;

    public ApplicationStartupRunner(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        final Optional<User> existsAdmin = userRepository.findByEmail(ADMIN_EMAIL);
        if (existsAdmin.isEmpty()) {
            final User adminUser = new User("admin", "admin", ADMIN_EMAIL, Role.ADMIN);
            userRepository.save(adminUser);
        }

    }
}
