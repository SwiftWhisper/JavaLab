package sumdu.edu.ua;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonLoader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode loadJson(String filePath) throws IOException {
        return mapper.readTree(new File(filePath));
    }
}
