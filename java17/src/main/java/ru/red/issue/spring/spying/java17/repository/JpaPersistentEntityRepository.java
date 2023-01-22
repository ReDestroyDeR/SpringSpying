package ru.red.issue.spring.spying.java17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.red.issue.spring.spying.java17.domain.JpaPersistentEntity;

import java.util.Optional;
import java.util.UUID;

public interface JpaPersistentEntityRepository extends JpaRepository<JpaPersistentEntity, UUID> {

    default Optional<JpaPersistentEntity> richSave(JpaPersistentEntity persistentEntity) {
        // Any logic. Just an example
        return Optional.ofNullable(persistentEntity)
                .map(JpaPersistentEntity::getId)
                .flatMap(this::findById) // Key is we are delegating to JpaRepository methods
                .map(entity -> entity.concat(persistentEntity))
                .map(this::save);
    }

}
