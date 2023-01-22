package ru.red.issue.spring.spying.java11.cfg;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static ru.red.issue.spring.spying.java11.cfg.CustomJpaConfig.BASE_ENTITY_PKG;
import static ru.red.issue.spring.spying.java11.cfg.CustomJpaConfig.BASE_PKG;

@Configuration
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
@EnableJpaAuditing
@EnableJpaRepositories(BASE_PKG)
@ComponentScan(BASE_PKG)
@EntityScan(BASE_ENTITY_PKG)
public class CustomJpaConfig {
    public static final String BASE_PKG = "ru.red.issue.spring.spying.java11.repository";
    public static final String BASE_ENTITY_PKG = "ru.red.issue.spring.spying.java11.domain";

}
