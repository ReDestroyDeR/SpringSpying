package ru.red.issue.spring.spying.java17.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class JpaPersistentEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "payload")
    private String payload;

    public JpaPersistentEntity concat(JpaPersistentEntity pe) {
        if (pe == null)
            return null;

        this.payload += pe.getPayload();

        return this;
    }
}
