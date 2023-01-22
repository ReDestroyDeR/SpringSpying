package ru.red.issue.spring.spying.java11.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Document
public class MongoPersistentEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId id;

    @Column(name = "payload")
    private String payload;

    public MongoPersistentEntity concat(MongoPersistentEntity pe) {
        if (pe == null)
            return null;

        this.payload += pe.getPayload();

        return this;
    }
}
