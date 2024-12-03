package io.mpolivaha.service;

import io.mpolivaha.models.SimpleEntity;
import io.mpolivaha.repository.SimpleEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class SimpleEntityService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SimpleEntityRepository simpleEntityRepository;

    public void question_1() {
        var entity = new SimpleEntity()
                .setId(1L)
                .setName("some name")
                .setType("type");

        entityManager.persist(entity);
    }

    public void question_2() {
        var entity = new SimpleEntity()
                .setId(1L)
                .setName("some name")
                .setType("type");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(entity);
        transaction.commit();
    }

    @Transactional
    public void question_3() {
        var entity = new SimpleEntity()
                .setId(1L)
                .setName("some name")
                .setType("type");

        entityManager.persist(entity);
    }

    public void question_4() {
        var entity = new SimpleEntity()
                .setId(1L)
                .setName("some name")
                .setType("type");

        simpleEntityRepository.deleteById(entity.getId());
    }

    public void question_5(String type) {
        simpleEntityRepository
                .findAllByType(type)
                .map(SimpleEntity::getName)
                .distinct()
                .forEach(System.out::println);
    }

    public Stream<String> question_6(String type) {
        return simpleEntityRepository
                .findAllByType(type)
                .map(SimpleEntity::getName)
                .distinct();
    }
}
