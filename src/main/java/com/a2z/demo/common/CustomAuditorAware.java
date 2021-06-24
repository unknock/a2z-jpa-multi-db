package com.a2z.demo.common;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class CustomAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#auditing.interfaces
        return Optional.of("Brian");
    }
}
