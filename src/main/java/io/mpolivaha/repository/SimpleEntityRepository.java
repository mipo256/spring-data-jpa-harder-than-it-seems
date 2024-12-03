package io.mpolivaha.repository;

import io.mpolivaha.models.SimpleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface SimpleEntityRepository extends JpaRepository<SimpleEntity, Long> {

    Stream<SimpleEntity> findAllByType(String type);
}
