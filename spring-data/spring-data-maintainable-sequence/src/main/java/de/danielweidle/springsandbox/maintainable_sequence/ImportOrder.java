package de.danielweidle.springsandbox.maintainable_sequence;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "import_order")
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImportOrder {

    static ImportOrder random(ImportOrder predecessor) {
        return ImportOrder.builder().successor(predecessor).build();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private long rev;

    private String orderId;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    ImportOrder successor;
}
