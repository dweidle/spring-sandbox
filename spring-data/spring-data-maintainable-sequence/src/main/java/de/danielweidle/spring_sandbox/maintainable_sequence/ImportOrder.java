package de.danielweidle.spring_sandbox.maintainable_sequence;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "import_order")
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ImportOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();

    private LocalDateTime createTs;
    private LocalDateTime updateTs;

    @Version
    private long rev;

    private String orderId;

    UUID successorId;
    UUID predecessorId;

    @PreUpdate
    @PrePersist
    public void prePersiste() {
        if (createTs == null) createTs = LocalDateTime.now();
        updateTs = LocalDateTime.now();
    }
}
