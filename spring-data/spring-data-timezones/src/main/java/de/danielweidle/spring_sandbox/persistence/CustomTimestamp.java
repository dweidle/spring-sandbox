package de.danielweidle.spring_sandbox.persistence;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Value;

import javax.persistence.Embeddable;
import java.io.IOException;
import java.io.Serializable;
import java.time.*;

@Value
@Embeddable
@JsonSerialize(using = CustomTimestamp.CustomTimestampSerializer.class)
public class CustomTimestamp implements Serializable {

    OffsetDateTime timestampUtc = OffsetDateTime.now(ZoneOffset.UTC);
    int offsetMinutesToUtc = offsetMinutesToUtc();

    private static int offsetMinutesToUtc() {
        int totalSeconds = ZonedDateTime.now().getOffset().getTotalSeconds();
        return totalSeconds == 0 ? 0 : totalSeconds / 60;
    }

    private OffsetDateTime atOrigin() {
        return timestampUtc.withOffsetSameInstant(ZoneOffset.ofTotalSeconds(offsetMinutesToUtc * 60));
    }

    @Override
    public String toString() {
        return atOrigin().toString();
    }

    public static class CustomTimestampSerializer extends JsonSerializer<CustomTimestamp> {

        @Override
        public void serialize(CustomTimestamp customTimestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(customTimestamp.toString());
        }
    }
}
