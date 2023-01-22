package ru.red.issue.spring.spying.java11.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.red.issue.spring.spying.java11.container.PostgresContainerizedTest;
import ru.red.issue.spring.spying.java11.domain.JpaPersistentEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

class JpaPersistentEntityRepositoryTest extends PostgresContainerizedTest {

    @Spy
    @Autowired
    private JpaPersistentEntityRepository repository;

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