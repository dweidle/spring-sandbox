package de.danielweidle.springsandbox.maintainable_sequence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
class ImportOrderController {

    private final ImportOrderRepository repository;

    @GetMapping
    public ResponseEntity<Page<ImportOrder>> getOrders(@RequestParam("count") int count) {
        long start = System.currentTimeMillis();
        Page<ImportOrder> res = repository.findAll(Pageable.ofSize(count));
        long end = System.currentTimeMillis();

        log.info("took {}ms", end - start);
        return ResponseEntity.ok(res);
    }
}
