package com.pe.walavo.challenge.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class ConvertUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static <T> T fileToObject(String path, Class<T> objectResponse) throws IOException {
        return objectMapper.readValue(ConvertUtil.class.getClassLoader().getResourceAsStream(path), objectResponse);
    }

    public static String jsonToString(final Object obj) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T stringToObject(String json, Class<T> objectResponse) throws IOException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.readValue(json, objectResponse);
    }


    public static <T> T stringToObjectTransport(String json, Class<T> objectResponse) throws IOException {
        return objectMapper.readValue(json, objectResponse);
    }

    public <T> T decodeObjectTransport(String raw, Class<T> oTransport) throws JsonProcessingException {
        return objectMapper.readValue(raw, oTransport);
    }
}
