package utils;

import com.angularBootRef.springBootPortfolio.domain.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonMapper<T> {

    private final ObjectMapper objectMapper;

    public JsonMapper() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.disable(SerializationFeature.INDENT_OUTPUT);

        final SimpleModule module = new SimpleModule("", Version.unknownVersion());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        this.objectMapper.registerModule(module);
    }

    public String fromObjectToJson(T obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public String fromObjectListToJson(List<T> objList) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        this.objectMapper.writeValue(out, objList);
        final byte[] data = out.toByteArray();
        return new String(data);
    }

    public T fromJsonToObject(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    //This will return a linked HashSet bug so need to pass and typeReference is a slower runtime
    public List<T> fromJsonToObjectList(String json) throws IOException {
        return objectMapper.readValue(json, new TypeReference<List<T>>() {
        });
    }

    public <T> List<T> fromJsonArrayToObjectList(String json, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
        List<T> ts = mapper.readValue(json, listType);
        return ts;
    }

}
