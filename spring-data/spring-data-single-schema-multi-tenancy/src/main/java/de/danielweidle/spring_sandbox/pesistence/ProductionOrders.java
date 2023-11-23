package de.danielweidle.spring_sandbox.pesistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionOrders extends JpaRepository<ProductionOrder, Long> {
}
