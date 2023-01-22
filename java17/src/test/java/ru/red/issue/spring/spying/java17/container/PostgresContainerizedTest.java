package ru.red.issue.spring.spying.java17.container;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.red.issue.spring.spying.java17.annotation.CustomJpaTest;

@CustomJpaTest
public abstract class PostgresContainerizedTest {
    private static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:12.10");

    @BeforeAll
    static void setUpAll() {
        if (CONTAINER.isRunning())
            return;

        CONTAINER.start();
        Runtime.getRuntime().addShutdownHook(new Thread(PostgresContainerizedTest::shutdown));
    }

    static void shutdown() {
        if (!CONTAINER.isShouldBeReused())
            CONTAINER.stop();
    }

    @DynamicPropertySource
    public static void dps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
        registry.add("spring.datasource.database", CONTAINER::getDatabaseName);
        registry.add("spring.datasource.driver-class-name", CONTAINER::getDriverClassName);
    }

}
