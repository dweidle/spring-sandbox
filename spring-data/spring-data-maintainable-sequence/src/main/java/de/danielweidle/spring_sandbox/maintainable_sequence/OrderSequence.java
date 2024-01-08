package de.danielweidle.spring_sandbox.maintainable_sequence;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
class OrderSequence extends LinkedList<ImportOrder> {

    private final Map<UUID, ImportOrder> index = new HashMap<>();

    public OrderSequence(Collection<ImportOrder> orderCollection) {
        orderCollection.forEach(this::add);
    }

    public ImportOrder moveToStart(UUID orderId) {
        ImportOrder orderToMove = index.get(orderId);
        return this.moveToStart(orderToMove);
    }

    public ImportOrder moveToStart(ImportOrder order) {
        ImportOrder predecessor = index.get(order.getPredecessorId());
        ImportOrder sucessor = index.get(order.getSuccessorId());
        ImportOrder first = getFirst();
        predecessor.setSuccessorId(sucessor.getId());
        sucessor.setPredecessorId(predecessor.getId());
        first.setPredecessorId(order.getId());
        order.setPredecessorId(null);
        order.setSuccessorId(first.getId());
        addFirst(order);
        return order;
    }

    @Override
    public boolean add(ImportOrder importOrder) {
        final boolean added;
        if (this.size() != 0) {
            ImportOrder last = this.getLast();
            added = super.add(importOrder);
            importOrder.setPredecessorId(last.getId());
            last.setSuccessorId(importOrder.getId());
        } else {
            added = super.add(importOrder);
        }
        index.put(importOrder.getId(), importOrder);
        return added;
    }
}
