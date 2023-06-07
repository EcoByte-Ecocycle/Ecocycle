package com.ecobyte.ecocycle.domain.user;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Stamp {

    private static final Integer LIMIT_MAX = 40;

    private final Integer stamps;

    public Stamp() {
        this.stamps = 0;
    }
}
