package de.danielweidle.springsandbox.maintainable_sequence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootApplication
public class MaintainableSequenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaintainableSequenceApplication.class, args);
    }



    @Slf4j
    @Component
    @RequiredArgsConstructor
    static class PopulateDatabase implements ApplicationRunner {

        private final ImportOrderRepository repo;
        private final EntityManager em;

        @Transactional
        @Override
        public void run(ApplicationArguments args) {
            ImportOrder last = repo.save(ImportOrder.random(null));
            for (int i = 0; i < 10000; i++) {
                ImportOrder lastRef = repo.getReferenceById(last.getId());
                last = repo.save(ImportOrder.random(lastRef));
                if (i % 100 == 0) {
                    log.info("Saved 100");
                }
            }
            repo.flush();
            em.clear();
        }
    }

}
