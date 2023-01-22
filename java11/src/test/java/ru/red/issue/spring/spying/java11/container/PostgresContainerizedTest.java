package ru.red.issue.spring.spying.java11.container;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.red.issue.spring.spying.java11.annotation.CustomJpaTest;

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
