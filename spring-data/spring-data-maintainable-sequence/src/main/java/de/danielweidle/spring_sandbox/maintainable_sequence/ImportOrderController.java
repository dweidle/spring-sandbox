package de.danielweidle.spring_sandbox.maintainable_sequence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Transactional
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
class ImportOrderController {

    private final ImportOrderRepository repository;

    @GetMapping
    public ResponseEntity<Page<ImportOrder>> getOrders(@RequestParam("count") int count) {
        long start = System.currentTimeMillis();
        OrderSequence os = new OrderSequence(repository.findAll());
        long end = System.currentTimeMillis();
        log.info("took {}ms", end - start);
        return ResponseEntity.ok(new PageImpl<>(os));
    }

    @GetMapping("/to-start")
    public ResponseEntity<ImportOrder> toStart(@RequestParam("id") UUID id) {
        OrderSequence os = new OrderSequence(repository.findAll());
        ImportOrder importOrder = os.moveToStart(id);
        return ResponseEntity.ok(importOrder);
    }
}
