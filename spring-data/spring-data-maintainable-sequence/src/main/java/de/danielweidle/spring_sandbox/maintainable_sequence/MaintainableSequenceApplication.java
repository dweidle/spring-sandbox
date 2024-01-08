package de.danielweidle.spring_sandbox.maintainable_sequence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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

            OrderSequence os = new OrderSequence();
            for(int i = 0; i < 10; i++) {
                os.add(new ImportOrder());
            }
            repo.saveAll(os);
            repo.flush();
            em.clear();
        }
    }

}
