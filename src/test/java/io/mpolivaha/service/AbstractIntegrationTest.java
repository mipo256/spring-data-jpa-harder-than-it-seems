package io.mpolivaha.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class AbstractIntegrationTest {

    protected static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.3"));

    @DynamicPropertySource
    void register(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        CONTAINER.start();
    }

    @AfterAll
    static void afterAll() {
        CONTAINER.stop();
    }
}
