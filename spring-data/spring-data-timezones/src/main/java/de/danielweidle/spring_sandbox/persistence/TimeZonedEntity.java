package de.danielweidle.spring_sandbox.persistence;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "time_zoned_entity")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeZonedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    LocalDateTime now;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "timestampUtc", column = @Column(name = "some_date_ts_utc", columnDefinition = "TIMESTAMP WITH TIME ZONE")),
            @AttributeOverride(name = "offsetMinutesToUtc", column = @Column(name = "some_date_offset_minutes_to_utc"))
    })
    Mo360MesTimestamp someDate = new Mo360MesTimestamp();
}
