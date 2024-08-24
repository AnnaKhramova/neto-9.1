package com.example.task_9_1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    @Container
    private final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @Test
    void devTest() {
        ResponseEntity<String> forEntityFromDev = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080) + "/profile", String.class);
        System.out.println(forEntityFromDev.getBody());
    }

    @Test
    void prodTest() {
        ResponseEntity<String> forEntityFromProd = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8081) + "/profile", String.class);
        System.out.println(forEntityFromProd.getBody());
    }

}
