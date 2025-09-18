package br.dgs.hanckathon.ia_market.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules()
            .enable(SerializationFeature.INDENT_OUTPUT);


    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro convertendo objeto para JSON", e);
        }
    }


    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro convertendo JSON para objeto", e);
        }
    }


    public static <T> T fromFile(File file, Class<T> clazz) {
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Erro lendo arquivo JSON", e);
        }
    }


    public static void toFile(Object obj, File file) {
        try {
            objectMapper.writeValue(file, obj);
        } catch (IOException e) {
            throw new RuntimeException("Erro salvando objeto em arquivo JSON", e);
        }
    }
}