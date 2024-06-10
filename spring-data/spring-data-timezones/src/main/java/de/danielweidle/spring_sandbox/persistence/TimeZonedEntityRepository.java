package de.danielweidle.spring_sandbox.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeZonedEntityRepository extends JpaRepository<TimeZonedEntity, Long> {
}
