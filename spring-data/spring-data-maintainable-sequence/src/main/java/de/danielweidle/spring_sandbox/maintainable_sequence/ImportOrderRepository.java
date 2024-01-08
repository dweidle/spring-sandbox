package de.danielweidle.spring_sandbox.maintainable_sequence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImportOrderRepository extends JpaRepository<ImportOrder, Long> {

    @Query("SELECT io FROM ImportOrder io")
    Page<ImportOrder> findAllDepthOne(Pageable pageable);
}
