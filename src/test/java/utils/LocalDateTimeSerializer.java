package utils;

import com.angularBootRef.springBootPortfolio.utils.DateTimeFormatUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormatUtils.DATE_TIME_FORMAT);
        jsonGenerator.writeString(localDateTime.format(formatter));
    }
}
