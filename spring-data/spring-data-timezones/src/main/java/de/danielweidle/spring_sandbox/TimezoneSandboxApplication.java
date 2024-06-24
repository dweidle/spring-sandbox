package de.danielweidle.spring_sandbox;

import de.danielweidle.spring_sandbox.persistence.CustomTimestamp;
import de.danielweidle.spring_sandbox.persistence.TimeZonedEntity;
import de.danielweidle.spring_sandbox.persistence.TimeZonedEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.*;
import java.util.List;

@SpringBootApplication
public class TimezoneSandboxApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimezoneSandboxApplication.class, args);
    }

    @Slf4j
    @RestController
    @RequiredArgsConstructor
    static class Controller {

        private final TimeZonedEntityRepository repo;

        @GetMapping
        public ResponseEntity<List<TimeZonedEntity>> get() {
            return ResponseEntity.ok(repo.findAll());
        }
    }


    @Slf4j
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class Initializer implements InitializingBean {

        private final TimeZonedEntityRepository repo;

        @Override
        public void afterPropertiesSet() throws Exception {
            repo.saveAndFlush(new TimeZonedEntity(1, "Timezone specified with now()", LocalDateTime.now(), new CustomTimestamp()));
        }
    }
}