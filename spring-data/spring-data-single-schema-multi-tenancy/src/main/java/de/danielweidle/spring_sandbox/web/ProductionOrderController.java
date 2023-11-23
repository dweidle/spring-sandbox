package de.danielweidle.spring_sandbox.web;

import de.danielweidle.spring_sandbox.TenantContext;
import de.danielweidle.spring_sandbox.pesistence.ProductionOrder;
import de.danielweidle.spring_sandbox.pesistence.ProductionOrdersPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class ProductionOrderController {

    private final ProductionOrdersPersistenceAdapter adapter;

    @GetMapping(path = "/{tenant}")
    public ResponseEntity<Collection<ProductionOrder>> get(@PathVariable("tenant") final String tenant) {
        TenantContext.setTenant(tenant);
        Collection<ProductionOrder> allPerTenant = adapter.findAllPerTenant(tenant);
        return ResponseEntity.ok(allPerTenant);
    }
}
