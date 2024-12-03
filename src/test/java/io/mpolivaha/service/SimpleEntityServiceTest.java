package io.mpolivaha.service;

import io.mpolivaha.models.SimpleEntity;
import io.mpolivaha.repository.SimpleEntityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.stream.Stream;

@SpringBootTest
class SimpleEntityServiceTest {

    @Autowired
    private SimpleEntityService simpleEntityService;

    @Autowired
    private SimpleEntityRepository simpleEntityRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @BeforeEach
    void setUp() {
        simpleEntityRepository.deleteAll(); // fine for tests and pretty small datasets
    }

    @Test
    void test_saveV1() {
        Assertions.assertThatCode(() -> simpleEntityService.question_1()).doesNotThrowAnyException();
    }

    @Test
    void test_question2() {
        Assertions.assertThatCode(() -> simpleEntityService.question_2()).doesNotThrowAnyException();
    }

    @Test
    void test_question3() {
        Assertions.assertThatCode(() -> simpleEntityService.question_3()).doesNotThrowAnyException();
    }

    @Test
    void test_question4() {
        Assertions.assertThatCode(() -> simpleEntityService.question_4()).doesNotThrowAnyException();
    }

    @Test
    void question_5() {
        simpleEntityRepository.save(new SimpleEntity()
                .setId(1L)
                .setName("some name")
                .setType("TYPE"));
        simpleEntityRepository.save(new SimpleEntity()
                .setId(2L)
                .setName("some name")
                .setType("TYPE"));
        Assertions.assertThatCode(() -> simpleEntityService.question_5("TYPE")).doesNotThrowAnyException();
    }

    @Test
    void question5_transactional() {
        final int amount = 1_000;

        transactionTemplate.executeWithoutResult(ts -> {
            for (long i = 0; i < amount; i++) {
                simpleEntityRepository.save(new SimpleEntity()
                        .setId(i)
                        .setName("%d name".formatted(i))
                        .setType("TYPE"));
            }
        });

        System.out.println("----------------- TESTING -----------------");

        transactionTemplate.executeWithoutResult(ts -> {
            Stream<String> stream = simpleEntityService.question_6("TYPE");
            Assertions.assertThat(stream).hasSize(amount);
        });
    }
}