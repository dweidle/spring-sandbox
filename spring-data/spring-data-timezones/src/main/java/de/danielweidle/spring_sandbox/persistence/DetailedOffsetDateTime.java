package de.danielweidle.spring_sandbox.persistence;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.IOException;
import java.io.Serializable;
import java.time.*;

@Data
@Embeddable
@JsonSerialize(using = DetailedOffsetDateTime.DetailedOffsetDateTimeSerializer.class)
public class DetailedOffsetDateTime implements Serializable {

    OffsetDateTime measuredAt = OffsetDateTime.now();
    int offsetToUtc = offsetToUtc();

    private static int offsetToUtc() {
        int totalSeconds = ZonedDateTime.now().getOffset().getTotalSeconds();
        return totalSeconds == 0 ? 0 : totalSeconds / 3600;
    }

    private OffsetDateTime atOrigin() {
        return measuredAt.withOffsetSameInstant(ZoneOffset.ofHours(offsetToUtc));
    }

    @Override
    public String toString() {
        return atOrigin().toString();
    }

    public static class DetailedOffsetDateTimeSerializer extends JsonSerializer<DetailedOffsetDateTime> {

        @Override
        public void serialize(DetailedOffsetDateTime detailedOffsetDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(detailedOffsetDateTime.toString());
        }
    }
}
