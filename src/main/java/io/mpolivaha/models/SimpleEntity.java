package io.mpolivaha.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * TODO: generate equals and hashcode
 */
@Data
@Accessors(chain = true)
@Entity
public class SimpleEntity {

    @Id
    private Long id;
    private String name;
    private String type;
}
