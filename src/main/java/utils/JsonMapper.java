package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T parse(final String json, final Class<T> tClass) throws IOException {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(json, tClass);
    }

    public static String parseToString(final Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }
}
