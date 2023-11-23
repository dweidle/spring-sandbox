package de.danielweidle.spring_sandbox.pesistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Random;

@Component
@Transactional
@RequiredArgsConstructor
public class ProductionOrdersPersistenceAdapter {

    private static final Random RANDOM = new Random();

    private final ProductionOrders productionOrders;

    public Collection<ProductionOrder> findAll() {
        return productionOrders.findAll();
    }

    public Collection<ProductionOrder> findAllPerTenant(String tenant) {
        return productionOrders.findAll();
    }

    public ProductionOrder saveRandom() {
        ProductionOrder productionOrder = ProductionOrder.builder()
                .orderNumber("R" + RANDOM.nextInt(1))
                .tenant(randomLn())
                .build();
        return productionOrders.save(productionOrder);
    }

    private static String randomLn() {
        switch (RANDOM.nextInt() % 3) {
            case 0:
                return "LN1";
            case 1:
                return "LN2";
            case 2:
                return "LN3";
        }
        return "LN0";
    }
}
