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
@JsonSerialize(using = Mo360MesTimestamp.Mo360MesTimestampSerializer.class)
public class Mo360MesTimestamp implements Serializable {

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

    public static class Mo360MesTimestampSerializer extends JsonSerializer<Mo360MesTimestamp> {

        @Override
        public void serialize(Mo360MesTimestamp mo360MesTimestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(mo360MesTimestamp.toString());
        }
    }
}
