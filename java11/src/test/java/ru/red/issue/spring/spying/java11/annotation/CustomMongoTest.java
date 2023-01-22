package ru.red.issue.spring.spying.java11.annotation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.red.issue.spring.spying.java11.cfg.CustomJpaConfig;
import ru.red.issue.spring.spying.java11.cfg.CustomMongoConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CustomMongoConfig.class
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Transactional(propagation = Propagation.REQUIRES_NEW, transactionManager = "mongoTransactionManager")
@ExtendWith(MockitoExtension.class)
public @interface CustomMongoTest {
}
