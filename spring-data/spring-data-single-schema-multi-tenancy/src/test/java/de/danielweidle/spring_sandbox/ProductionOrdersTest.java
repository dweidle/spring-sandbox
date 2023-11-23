package de.danielweidle.spring_sandbox;

import de.danielweidle.spring_sandbox.pesistence.ProductionOrdersPersistenceAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductionOrdersTest {

    @Autowired
    EntityManager em;
    @Autowired
    ProductionOrdersPersistenceAdapter productionOrders;


    @Test
    @Sql("createOrders.sql")
    public void findAllWithoutTenantSpecifiaction() {
        assertThat(productionOrders.findAll()).hasSize(1000);
    }
}