package org.example.UtilityClasses;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;

public class JSONReader {

    @SneakyThrows
    public static JsonNode readJSON(HttpServletRequest req) {
        StringBuilder jsonBuilder = new StringBuilder();

        String line;

        while ((line = req.getReader().readLine()) != null) {
            jsonBuilder.append(line);
        }

        String jsonString = jsonBuilder.toString();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(jsonString);
    }
}
