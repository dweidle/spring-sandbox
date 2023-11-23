package de.danielweidle.spring_sandbox;

import de.danielweidle.spring_sandbox.pesistence.ProductionOrdersPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;


@SpringBootApplication
public class SingleSchemaMultiTenancyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SingleSchemaMultiTenancyApplication.class);
    }

    @Component
    @RequiredArgsConstructor
    class Initialise implements ApplicationListener<ApplicationReadyEvent> {

        private final ProductionOrdersPersistenceAdapter adapter;

        @Override
        public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
            IntStream.range(0,20).forEach(i -> adapter.saveRandom());
        }
    }
}
