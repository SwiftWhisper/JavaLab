package sumdu.edu.ua.service.Json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonLoader {
    private final ObjectMapper objectMapper;

    public JsonLoader (ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonNode loadJson(String filePath) throws IOException {
        return objectMapper.readTree(new File(filePath));
    }
}
