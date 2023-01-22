package ru.red.issue.spring.spying.java17.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.mockito.internal.creation.bytebuddy.InlineByteBuddyMockMaker;
import org.springframework.beans.factory.annotation.Autowired;
import ru.red.issue.spring.spying.java17.container.PostgresContainerizedTest;
import ru.red.issue.spring.spying.java17.domain.JpaPersistentEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

@Slf4j
class JpaPersistentEntityRepositoryTest extends PostgresContainerizedTest {

    @Spy
    @Autowired
    private JpaPersistentEntityRepository repository;

    @Test
    void repo_isSpy_test() {
        assertTrue(mockingDetails(repository).isSpy());
    }

    @Test
    void repo_isMockable_test() {
        assertTrue(new InlineByteBuddyMockMaker().isTypeMockable(repository.getClass()).mockable());
        // Exception is caused after test
        log.info("Should get here");
    }

    @Test
    void richSave_repositoryHas3Invocations_test() {
        final var entity = mock(JpaPersistentEntity.class);
        final var id = mock(UUID.class);

        when(entity.getId()).thenReturn(id);
        when(repository.findById(id))
                .thenReturn(Optional.of(entity));
        when(entity.concat(any())).thenReturn(entity);

        repository.richSave(entity);

        final var details = mockingDetails(repository);
        final var invocations = details.getInvocations();

        assertEquals(3, invocations.size());
    }

}