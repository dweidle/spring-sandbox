package de.danielweidle.springsandbox.maintainable_sequence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportOrderRepository extends JpaRepository<ImportOrder, Long> {
}
