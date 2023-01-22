package ru.red.issue.spring.spying.java11.container;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.red.issue.spring.spying.java11.annotation.CustomJpaTest;
import ru.red.issue.spring.spying.java11.annotation.CustomMongoTest;

@CustomMongoTest
public abstract class MongoContainerizedTest {
    private static final MongoDBContainer CONTAINER = new MongoDBContainer("mongo:4.4.12");

    @BeforeAll
    static void setUpAll() {
        if (CONTAINER.isRunning())
            return;

        CONTAINER.start();
        Runtime.getRuntime().addShutdownHook(new Thread(MongoContainerizedTest::shutdown));
    }

    static void shutdown() {
        if (!CONTAINER.isShouldBeReused())
            CONTAINER.stop();
    }

    @DynamicPropertySource
    public static void dps(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", CONTAINER::getReplicaSetUrl);
    }

}
