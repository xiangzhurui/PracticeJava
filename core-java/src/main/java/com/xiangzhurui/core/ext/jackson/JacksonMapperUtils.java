package com.xiangzhurui.core.ext.jackson;


import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class JacksonMapperUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    static {
        XML_MAPPER.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    }

    private JacksonMapperUtils() {
    }

    public static Optional<String> toJSON(Object object) {
        try {
            String s = OBJECT_MAPPER.writeValueAsString(object);
            return Optional.of(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static <T> Optional<T> fromJson(String jsonStr, Class<T> tClass) {
        try {
            T value = OBJECT_MAPPER.readValue(jsonStr, tClass);
            return Optional.of(value);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<String> toXML(Object object) {
        try {
            String s = XML_MAPPER.writeValueAsString(object);
            return Optional.of(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


}
