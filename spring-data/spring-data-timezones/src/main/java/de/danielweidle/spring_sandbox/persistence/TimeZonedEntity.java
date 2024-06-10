package de.danielweidle.spring_sandbox.persistence;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "time_zoned_entity")
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeZonedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    OffsetDateTime offsetDateTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "measuredAt", column = @Column(name = "some_date_measured_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")),
            @AttributeOverride( name = "offsetToUtc", column = @Column(name = "some_date_offset_to_utc"))
    })
    DetailedOffsetDateTime someDate = new DetailedOffsetDateTime();
}
