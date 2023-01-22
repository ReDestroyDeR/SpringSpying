package ru.red.issue.spring.spying.java17.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.red.issue.spring.spying.java17.domain.MongoPersistentEntity;

import java.util.Optional;

public interface MongoPersistentEntityRepository extends MongoRepository<MongoPersistentEntity, ObjectId> {

    default Optional<MongoPersistentEntity> richSave(MongoPersistentEntity persistentEntity) {
        // Any logic. Just an example
        return Optional.ofNullable(persistentEntity)
                .map(MongoPersistentEntity::getId)
                .flatMap(this::findById) // Key is we are delegating to MongoRepository methods
                .map(entity -> entity.concat(persistentEntity))
                .map(this::save);
    }

}
