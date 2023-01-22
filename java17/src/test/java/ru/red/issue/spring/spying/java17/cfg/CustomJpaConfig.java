package ru.red.issue.spring.spying.java17.cfg;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
@EnableJpaAuditing
@EnableJpaRepositories(CustomJpaConfig.BASE_PKG)
@ComponentScan(CustomJpaConfig.BASE_PKG)
@EntityScan(CustomJpaConfig.BASE_ENTITY_PKG)
public class CustomJpaConfig {
    public static final String BASE_PKG = "ru.red.issue.spring.spying.java17.repository";
    public static final String BASE_ENTITY_PKG = "ru.red.issue.spring.spying.java17.domain";

}
