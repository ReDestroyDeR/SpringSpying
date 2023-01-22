package ru.red.issue.spring.spying.java11.repository;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.red.issue.spring.spying.java11.container.MongoContainerizedTest;
import ru.red.issue.spring.spying.java11.container.PostgresContainerizedTest;
import ru.red.issue.spring.spying.java11.domain.MongoPersistentEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

@Slf4j
class MongoJpaPersistentEntityRepositoryTest extends MongoContainerizedTest {

    @Spy
    @Autowired
    private MongoPersistentEntityRepository repository;

    @Test
    void richSave_repositoryHas3Invocations_test() {
        final var entity = mock(MongoPersistentEntity.class);
        final var id = ObjectId.get();

        when(entity.getId()).thenReturn(id);
        when(repository.findById(id))
                .thenReturn(Optional.of(entity));
        when(entity.concat(any())).thenReturn(entity);

        repository.richSave(entity);

        final var details = mockingDetails(repository);
        details.printInvocations();

        final var invocations = details.getInvocations();
        assertEquals(3, invocations.size());
    }

}