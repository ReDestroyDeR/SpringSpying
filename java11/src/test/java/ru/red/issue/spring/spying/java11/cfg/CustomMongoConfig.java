package ru.red.issue.spring.spying.java11.cfg;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static ru.red.issue.spring.spying.java11.cfg.CustomJpaConfig.BASE_ENTITY_PKG;
import static ru.red.issue.spring.spying.java11.cfg.CustomJpaConfig.BASE_PKG;

@Configuration
@AutoConfigureDataMongo
@EnableMongoAuditing
@EnableMongoRepositories(BASE_PKG)
@ComponentScan(BASE_PKG)
public class CustomMongoConfig {
    public static final String BASE_PKG = "ru.red.issue.spring.spying.java11.repository";

    @Bean
    MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
