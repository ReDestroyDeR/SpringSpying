package ru.red.issue.spring.spying.java17.cfg;

import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static ru.red.issue.spring.spying.java17.cfg.CustomMongoConfig.BASE_PKG;

@Configuration
@AutoConfigureDataMongo
@EnableMongoAuditing
@EnableMongoRepositories(BASE_PKG)
@ComponentScan(BASE_PKG)
public class CustomMongoConfig {
    public static final String BASE_PKG = "ru.red.issue.spring.spying.java17.repository";

    @Bean
    MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
