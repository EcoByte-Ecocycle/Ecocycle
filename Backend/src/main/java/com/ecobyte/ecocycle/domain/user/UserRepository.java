package com.ecobyte.ecocycle.domain.user;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    User save(final User user);

    Optional<User> findById(final Long id);

    Optional<User> findByEmail(final String email);
}
