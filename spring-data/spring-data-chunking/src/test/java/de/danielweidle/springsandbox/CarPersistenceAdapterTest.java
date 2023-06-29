package de.danielweidle.springsandbox;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CarPersistenceAdapterTest {

    @Autowired CarPersistenceAdapter carPersistenceAdapter;

    @Test
    @Sql("createCars.sql")
    void name() {
        carPersistenceAdapter.findCarsChunked();
        System.out.println("finished");
    }
}